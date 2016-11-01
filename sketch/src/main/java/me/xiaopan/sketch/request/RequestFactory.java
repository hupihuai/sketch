/*
 * Copyright (C) 2013 Peng fei Pan <sky@xiaopan.me>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.xiaopan.sketch.request;

import android.text.TextUtils;

import me.xiaopan.sketch.Identifier;
import me.xiaopan.sketch.Sketch;

/**
 * Request创建工厂
 */
public class RequestFactory implements Identifier {
    protected String logName = "RequestFactory";

    public DisplayRequest newDisplayRequest(
            Sketch sketch, DisplayInfo requestInfo, DisplayAttrs displayAttrs,
            DisplayOptions displayOptions, RequestAndViewBinder requestAndViewBinder,
            DisplayListener displayListener, DownloadProgressListener downloadProgressListener) {
        // 由于DisplayHelper会被重复利用
        // 因此RequestAttrs、DisplayAttrs和DisplayOptions不能直接拿来用，要重新New一个
        return new DisplayRequest(
                sketch,
                new DisplayInfo(requestInfo),
                new DisplayAttrs(displayAttrs),
                new DisplayOptions(displayOptions),
                requestAndViewBinder, displayListener, downloadProgressListener);
    }

    public LoadRequest newLoadRequest(
            Sketch sketch, LoadInfo requestInfo, LoadOptions options,
            LoadListener listener, DownloadProgressListener downloadProgressListener) {
        return new LoadRequest(sketch, requestInfo, options, listener, downloadProgressListener);
    }

    public DownloadRequest newDownloadRequest(
            Sketch sketch, DownloadInfo requestInfo, DownloadOptions options,
            DownloadListener listener, DownloadProgressListener downloadProgressListener) {
        return new DownloadRequest(sketch, requestInfo, options, listener, downloadProgressListener);
    }

    @Override
    public String getIdentifier() {
        return logName;
    }

    @Override
    public StringBuilder appendIdentifier(String join, StringBuilder builder) {
        if (!TextUtils.isEmpty(join)) {
            builder.append(join);
        }
        return builder.append(logName);
    }
}