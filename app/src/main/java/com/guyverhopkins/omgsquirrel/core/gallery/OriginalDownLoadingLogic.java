package com.guyverhopkins.omgsquirrel.core.gallery;


import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


final class OriginalDownloadingLogic {

    private String FEED_URL = "https://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&oauth_consumer_key=54f1d0146bcec3b405164e253e8ff710&photoset_id=72157661135553275&user_id=137813339%40N03&format=json&nojsoncallback=1&oauth_token=72157663053868089-63166f4e59a184ca&api_sig=d50c3cc7a51f089c28c3efe145e22b6f4aa95f0c";

//        new AsyncHttpTask().execute(FEED_URL);

    String streamToString(InputStream stream) {
        String result = "";
        try {
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();

            String stringReadLine;
            while ((stringReadLine = bufferedreader.readLine()) != null) {
                stringBuilder.append(stringReadLine + "\n");
            }
            if (null != stream) {
                stream.close();
            }
            result += stringBuilder.toString();

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        return result;
    }

    void parseResult(String result) {

        try {
            JSONObject response = new JSONObject(result);
            JSONObject photoSet = response.getJSONObject("photoset");
            JSONArray photos = photoSet.getJSONArray("photo");

            GridItem item;
            for (int i = 0; i < photos.length(); i++) {
                item = new GridItem();
                JSONObject temp_photo = photos.optJSONObject(i);
                String id = temp_photo.getString("id");
                String secret = temp_photo.getString("secret");
                String server = temp_photo.getString("server");
                String farm = temp_photo.getString("farm");
                String title = temp_photo.getString("title");

                item.setTitle(title);
                item.setImage("http://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg");

//                mGridData.add(item);
            }

        } catch (JSONException e) {
            Log.d("didn't work", "Try again");
            e.printStackTrace();
        }
    }
    // Parsing the feed results and get the list
    // @param result

    //Downloading data asynchronously
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {


        protected Integer doInBackground(String... params) {
            Integer result = 0;
//            try {
            // Create Apache HttpClient
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpResponse httpResponse = httpclient.execute(new HttpGet(params[0]));
//                int statusCode = httpResponse.getStatusLine().getStatusCode();


            // 200 represents HTTP OK
//                if (statusCode == 200) { //was ==
//                    String response = streamToString(httpResponse.getEntity().getContent());
//                    parseResult(response);
//                    result = 1; // Successful
//                } else {
//                    result = 0;
//                }
//            } catch (Exception e) {
////                Log.d(TAG, e.getLocalizedMessage());
//            }
            return result;

        }

        //        @Override  //this happens last
        protected void onPostExecute(Integer result) { //Integer Result
            // Download complete. Let us update UI
            if (result == 1) {
//                mGridAdapter.setGridData(mGridData);
            } else {
//                DialogFactory.newOneBtnAlert(mContext, "Failed to load data, Check your internet connection");
            }
        }
    }
}