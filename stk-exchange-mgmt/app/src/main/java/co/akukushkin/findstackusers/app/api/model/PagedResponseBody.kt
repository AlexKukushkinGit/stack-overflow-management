package co.akukushkin.findstackusers.app.api.model

/**
 * Created by akukushkin on 22/09/2019.
 */

class PagedResponseBody<T>(val items: T, val hasMore: Boolean, val quotaMax: Int, val quotaRemaining: Int) {


}