package com.pubnub.publish.dependencies.other

import com.pubnub.publish.dependencies.models.PNFile

data class FileUploadRequestDetails(
    val status: Int,
    val data: PNFile,
    val url: String,
    val method: String,
    val expirationDate: String,
    val keyFormField: FormField,
    val formFields: List<FormField>
)
