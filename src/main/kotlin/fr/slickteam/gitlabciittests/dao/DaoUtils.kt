package fr.slickteam.gitlabciittests.dao

import org.litote.kmongo.formatJson

fun buildAggregateRequest(from: Int, limit: Int, orderBy: String, orderDir: String, filter: String): String {
    var requestFilter = ""
    if (filter.isNotEmpty()) {
        // TODO
    }
    var order = ""
    if (orderBy.isNotEmpty()) {
        val dir = if (orderDir.equals("ASC")) "1" else "-1"
        order = " { \$sort : { $orderBy : $dir } },"
    }
    val requestLimit = "{ \$limit : $limit }"
    return "[ $requestFilter $order $requestLimit ]".formatJson()
}