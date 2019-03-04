/*
 *    Copyright 2017 Duncan "duncte123" Sterken
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ml.duncte123.CleverBot4J;

import jdk.internal.jline.internal.Nullable;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebUtils {

    public static final String baseUrl = "https://cleverbot.io/1.0/";

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build();

    /**
     * This is a util to send json data to api
     * @param url the url to post to
     * @param data a {@link org.json.JSONObject JSONObject} with the data that you want to send
     * @return the response from the server wrapped in the okhttp {@link okhttp3.Response Response} class
     */
    @Nullable
    public static Response postJSON(String url, JSONObject data) {
        final RequestBody body = RequestBody.create(MediaType.parse("application/json"), data.toString());
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("User-Agent", "Java Cleverbot API (https://github.com/duncte123/CleverBot4J)")
                .build();

        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
