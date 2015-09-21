package com.example.rramirez.petercapussoto;

//Import for Youtube playing
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

//Import for Jsonretrival
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

public class menu_video extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

        // json object response url
        private String urlJsonObj = "http://api.androidhive.info/volley/person_object.json";

        private static String TAG = menu_video.class.getSimpleName();

        private TextView txtResponse;

        // temporary string to show the parsed response
        private String jsonResponse;


        //Set API key
        public static final String API_KEY = "AIzaSyAnqeTlwdhaTWDoWOXxtRtOydrXujz9jqo";

        //http://youtu.be/<VIDEO_ID>
        public static final String VIDEO_ID = "gW5pm9WoCXM";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            /** attaching layout xml **/
            setContentView(R.layout.activity_menu_video);

            /** Initializing YouTube player view **/
            YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
            youTubePlayerView.initialize(API_KEY, this);


            //Retrieve JSON
            txtResponse = (TextView) findViewById(R.id.txtResponse);
            makeJsonObjectRequest();



        }

        @Override
        public void onInitializationFailure(Provider provider, YouTubeInitializationResult result) {
            Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
            /** add listeners to YouTubePlayer instance **/
            player.setPlayerStateChangeListener(playerStateChangeListener);
            player.setPlaybackEventListener(playbackEventListener);

            /** Start buffering **/
            if (!wasRestored) {
                player.cueVideo(VIDEO_ID);
            }
        }

        private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

            @Override
            public void onBuffering(boolean arg0) {
            }

            @Override
            public void onPaused() {
            }

            @Override
            public void onPlaying() {
            }

            @Override
            public void onSeekTo(int arg0) {
            }

            @Override
            public void onStopped() {
            }

        };

        private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

            @Override
            public void onAdStarted() {
            }

            @Override
            public void onError(ErrorReason arg0) {
            }

            @Override
            public void onLoaded(String arg0) {
            }

            @Override
            public void onLoading() {
            }

            @Override
            public void onVideoEnded() {
            }

            @Override
            public void onVideoStarted() {
            }
        };


            //Make Json request
            private void makeJsonObjectRequest() {
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                        urlJsonObj, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        try {
                            // Parsing json object response
                            // response will be a json object
                            String name = response.getString("name");
                            String email = response.getString("email");
                            JSONObject phone = response.getJSONObject("phone");
                            String home = phone.getString("home");
                            String mobile = phone.getString("mobile");

                            jsonResponse = "";
                            jsonResponse += "Name: " + name + "\n\n";
                            jsonResponse += "Email: " + email + "\n\n";
                            jsonResponse += "Home: " + home + "\n\n";
                            jsonResponse += "Mobile: " + mobile + "\n\n";

                            txtResponse.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(jsonObjReq);
            }
    }

