package com.maheshgupta.analyticssdk.jobs;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class AnalyticsJobCreator implements JobCreator {
    @Override
    public Job create(String tag) {
        switch (tag) {
            case UploadDataJob.TAG:
                return new UploadDataJob();
            default:
                return null;
        }
    }
}
