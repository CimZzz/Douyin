import com.gogoh5.douyin.DouYinTool
import java.io.File

fun main(args: Array<String>) {
//    val url = "https://aweme-hl.snssdk.com/aweme/v1/discover/search/?os_api=23&device_type=Lenovo%20K10e70&ssmix=a&manifest_version_code=750&dpi=320&js_sdk_version=1.19.4.14&uuid=861733032630570&app_name=aweme&version_name=7.5.0&ts=1566542389&app_type=normal&ac=wifi&update_version_code=7502&channel=aweGW&_rticket=1566542390894&device_platform=android&iid=83379556251&version_code=750&openudid=fd29bec518ad766e&device_id=69276938351&resolution=720*1280&os_version=6.0.1&language=zh&device_brand=Lenovo&aid=1128&mcc_mnc=46000"
    val urb = "https://aweme-hl.snssdk.com/aweme/v1/discover/search/?vid=678F8B27-4B77-4321-8C50-30B6BD4D99F7&pass-region=1&channel=App%20Store&screen_width=768&app_name=aweme&device_type=iPad2,5&pass-route=1&openudid=936e37b5aaece474d6b88e386d49acda32f6e489&aid=1128&js_sdk_version=1.17.2.0&os_api=18&build_number=77019&os_version=9.3.5&device_platform=ipad&app_version=7.7.0&ac=WIFI&idfa=08658636-491F-4767-B067-276AD8FB6DA5&version_code=7.7.0&iid=83342936768&device_id=69299903772&mcc_mnc=&is_pull_refresh=0&type=1&hot_search=0&cursor=0&search_source=discover&query_correct_type=1&count=20&keyword=%E9%B9%BF%E6%99%97"
    val cookie = "odin_tt=3159c034c2390c57850c9fdc423af0ffaef051c9466a1e3f8a67ba353435977cde8f34b1c172252d7c8360d4027a1145; sid_guard=f59addaa893f10e271e3d17e7a61bedd%7C1566291382%7C5184000%7CSat%2C+19-Oct-2019+08%3A56%3A22+GMT; uid_tt=509f0c473af130b73c5cf22157f181ea; sid_tt=f59addaa893f10e271e3d17e7a61bedd; sessionid=f59addaa893f10e271e3d17e7a61bedd; qh[360]=1; install_id=83379556251; ttreq=1\$2cecd2128d59d0b4291307440efb9ae5a711c514"
//    val cookie = "install_id=83342936768; odin_tt=c06adfca2556f8fdeff81556cc63b08854f11271c81732b63c3f12be8fb3edbc46bcada7a2a99f2f5a7cefebdd1d651b1a8f8eeb6630664eb03e3a0476b8b81b; ttreq=1\$220113d959df2e0bd30c85ba8ba20adf6895118a"
    val body = "cursor=10&keyword=hello%E8%83%A1%E9%A6%A8%E5%AE%87&count=10&type=1&is_pull_refresh=1&hot_search=0&search_source=&search_id=20190823150523010028034152781840&query_correct_type=1"

//    val map = DouYinTool.get_X_Gorgon(urb, cookie)
//    val map = DouYinTool.post_X_Gorgon(url, cookie, body)

    println(buildString {
        append("curl ")
//        append("-H 'Host: api.amemv.com' ")
//        append("-H 'Host: aweme-hl.snssdk.com' ")
//        append("-d '$body' ")
//        append("-H 'content-type: application/x-www-form-urlencoded; charset=UTF-8' ")
//        append("-H 'content-length: 176' ")
//        append("-H 'x-ss-stub: 75B6F6B69BF2B20109DAC9DC9B06019E' ")
        append("-H 'Cookie: $cookie' ")
        append("-H 'sdk-version: 1' ")

        append("-H 'user-agent: com.ss.android.ugc.aweme/750 (Linux; U; Android 6.0.1; zh_CN; Lenovo K10e70; Build/MMB29M; Cronet/58.0.2991.0)' ")
//        append("-H 'user-agent: Aweme 7.7.0 rv:77019 (iPad; iPhone OS 9.3.5; zh_CN) Cronet' ")
//        append("-H 'x-khronos: ${map["X-Khronos"]}' ")
        append("-H 'x-khronos: 1566542307' ")
//        append("-H 'x-tt-trace-id: 00-1d76533335bf219b3721f6e132088996-1d76533335bf219b-01' ")
//        append("-H 'x-gorgon: ${map["X-Gorgon"]}' ")
        append("-H 'x-gorgon: 03008880200048d09b7d838ebee99a10e2177b22e6e03419f923' ")

        append("--compressed ")
        append("'$urb'")
//        append("'$urb'")
    })

}

//curl
//-H'Host: aweme-hl.snssdk.com'
//-H 'Cookie: install_id=83342936768; odin_tt=c06adfca2556f8fdeff81556cc63b08854f11271c81732b63c3f12be8fb3edbc46bcada7a2a99f2f5a7cefebdd1d651b1a8f8eeb6630664eb03e3a0476b8b81b; ttreq=1$220113d959df2e0bd30c85ba8ba20adf6895118a'
//-H 'sdk-version: 1'
//-H 'user-agent: Aweme 7.7.0 rv:77019 (iPad; iPhone OS 9.3.5; zh_CN) Cronet'
//-H 'x-tt-trace-id: 00-1d76533335bf219b3721f6e132088996-1d76533335bf219b-01'
//-H 'x-gorgon: 03008880200048d09b7d838ebee99a10e2177b22e6e03419f923'
//-H 'x-khronos: 1566542307'
//--compressed 'https://aweme-hl.snssdk.com/aweme/v1/discover/search/?vid=678F8B27-4B77-4321-8C50-30B6BD4D99F7&pass-region=1&channel=App%20Store&screen_width=768&app_name=aweme&device_type=iPad2,5&pass-route=1&openudid=936e37b5aaece474d6b88e386d49acda32f6e489&aid=1128&js_sdk_version=1.17.2.0&os_api=18&build_number=77019&os_version=9.3.5&device_platform=ipad&app_version=7.7.0&ac=WIFI&idfa=08658636-491F-4767-B067-276AD8FB6DA5&version_code=7.7.0&iid=83342936768&device_id=69299903772&mcc_mnc=&is_pull_refresh=0&type=1&hot_search=0&cursor=0&search_source=discover&query_correct_type=1&count=20&keyword=%E9%B9%BF%E6%99%97'

//curl -H 'Host: aweme-hl.snssdk.com' -H 'Cookie: install_id=83342936768; odin_tt=c06adfca2556f8fdeff81556cc63b08854f11271c81732b63c3f12be8fb3edbc46bcada7a2a99f2f5a7cefebdd1d651b1a8f8eeb6630664eb03e3a0476b8b81b; ttreq=1$220113d959df2e0bd30c85ba8ba20adf6895118a' -H 'sdk-version: 1' -H 'user-agent: Aweme 7.7.0 rv:77019 (iPad; iPhone OS 9.3.5; zh_CN) Cronet' -H 'x-tt-trace-id: 00-1d76533335bf219b3721f6e132088996-1d76533335bf219b-01' -H 'x-gorgon: 03008880200048d09b7d838ebee99a10e2177b22e6e03419f923' -H 'x-khronos: 1566542307' --compressed 'https://aweme-hl.snssdk.com/aweme/v1/discover/search/?vid=678F8B27-4B77-4321-8C50-30B6BD4D99F7&pass-region=1&channel=App%20Store&screen_width=768&app_name=aweme&device_type=iPad2,5&pass-route=1&openudid=936e37b5aaece474d6b88e386d49acda32f6e489&aid=1128&js_sdk_version=1.17.2.0&os_api=18&build_number=77019&os_version=9.3.5&device_platform=ipad&app_version=7.7.0&ac=WIFI&idfa=08658636-491F-4767-B067-276AD8FB6DA5&version_code=7.7.0&iid=83342936768&device_id=69299903772&mcc_mnc=&is_pull_refresh=0&type=1&hot_search=0&cursor=0&search_source=discover&query_correct_type=1&count=20&keyword=%E9%B9%BF%E6%99%97'
//curl -H 'Host: aweme-hl.snssdk.com' -H 'Cookie: install_id=83342936768; odin_tt=c06adfca2556f8fdeff81556cc63b08854f11271c81732b63c3f12be8fb3edbc46bcada7a2a99f2f5a7cefebdd1d651b1a8f8eeb6630664eb03e3a0476b8b81b; ttreq=1$220113d959df2e0bd30c85ba8ba20adf6895118a' -H 'sdk-version: 1' -H 'user-agent: Aweme 7.7.0 rv:77019 (iPad; iPhone OS 9.3.5; zh_CN) Cronet' -H 'x-khronos: 1566549025' -H 'x-gorgon: 03008880200048d09b7d838ebee99a10e2177b22e6e03419f923' -H 'x-tt-trace-id: 00-1d76533335bf219b3721f6e132088996-1d76533335bf219b-01' --compressed 'https://aweme-hl.snssdk.com/aweme/v1/discover/search/?vid=678F8B27-4B77-4321-8C50-30B6BD4D99F7&pass-region=1&channel=App%20Store&screen_width=768&app_name=aweme&device_type=iPad2,5&pass-route=1&openudid=936e37b5aaece474d6b88e386d49acda32f6e489&aid=1128&js_sdk_version=1.17.2.0&os_api=18&build_number=77019&os_version=9.3.5&device_platform=ipad&app_version=7.7.0&ac=WIFI&idfa=08658636-491F-4767-B067-276AD8FB6DA5&version_code=7.7.0&iid=83342936768&device_id=69299903772&mcc_mnc=&is_pull_refresh=0&type=1&hot_search=0&cursor=0&search_source=discover&query_correct_type=1&count=20&keyword=%E9%B9%BF%E6%99%97'
