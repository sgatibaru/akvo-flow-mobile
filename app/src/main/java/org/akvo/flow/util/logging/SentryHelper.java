/*
 * Copyright (C) 2010-2016 Stichting Akvo (Akvo Foundation)
 *
 * This file is part of Akvo FLOW.
 *
 * Akvo FLOW is free software: you can redistribute it and modify it under the terms of
 * the GNU Affero General Public License (AGPL) as published by the Free Software Foundation,
 * either version 3 of the License or any later version.
 *
 * Akvo FLOW is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License included below for more details.
 *
 * The full license text can also be seen at <http://www.gnu.org/licenses/agpl.html>.
 *
 */

package org.akvo.flow.util.logging;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.joshdholtz.sentry.Sentry;

import org.akvo.flow.util.ConstantUtil;
import org.akvo.flow.util.PropertyUtil;
import org.json.JSONException;

import java.util.Map;

import timber.log.Timber;

/**
 * Allows usage of the old sentry library
 */
public class SentryHelper extends LoggingHelper {

    private static final String SENTRY_BASE_URL = "http://sentry.support.akvo-ops.org/";

    public SentryHelper(Context context) {
        super(context);
    }

    @Override
    public void initSentry() {
        addTags();
        Sentry.setCaptureListener(new FlowSentryCaptureListener(tags));
        final PropertyUtil props = new PropertyUtil(context.getResources());
        String sentryBaseUrl = props.getProperty(ConstantUtil.SENTRY_URL);
        if (TextUtils.isEmpty(sentryBaseUrl)) {
            sentryBaseUrl = SENTRY_BASE_URL;
        }
        String sentryDsn = getSentryDsn(props);
        Sentry.init(context, sentryBaseUrl, sentryDsn);
    }

    @Override public void plantTimberTree() {
        Timber.plant(new SentryTree());
    }

    private static class FlowSentryCaptureListener extends Sentry.SentryEventCaptureListener {

        private final Map<String, String> tags;

        private FlowSentryCaptureListener(@NonNull Map<String, String> tags) {
            this.tags = tags;
        }

        @Override
        public Sentry.SentryEventBuilder beforeCapture(Sentry.SentryEventBuilder builder) {
            try {
                for (String key : tags.keySet()) {
                    builder.getTags().put(key, tags.get(key));
                }
            } catch (JSONException e) {
                Timber.e(e, "Error setting SentryEventCaptureListener");
            }

            return builder;
        }
    }
}
