package com.example.webdemo.xxljob.core.biz;

/**
 * @author tangaq
 * @date 2020/12/22
 */
public class RegistryTest {
    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                if (i == 2) {
                    break;
                }
            }
        }


        // String url = "http://127.0.0.1:8080/xxl-job-admin/api/registry";
        // RegistryParam registryParam = new RegistryParam();
        // try {
        //     // String s = HttpRequestUtil.sendPostByConn("http://127.0.0.1:8080/xxl-job-admin/api/registry", null, 60,
        //     //     registryParam);
        //     String s = HttpRequestUtil.sendPost(url, GsonUtil.obj2Json(registryParam));
        //     ReturnT<String> returnT = GsonUtil.json2Obj(s, ReturnT.class);
        //     System.out.println(s);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}
