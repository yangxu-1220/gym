package com.gym.config;

public class PayConfig {
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

		// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
		public static String app_id = "2016101200671126";
		
		// 商户私钥，您的PKCS8格式RSA2私钥
	    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgkpmwty4TVEkGX29hLEd3XROQkatjXOnwWtZJQHw7UDEN9PYeJZVq2DJwlqM42TFIKz7jKNe2ha5amddeq5rEbnOHWB598rjXN9XS6SBL2G5nY+NHtQrmgPrpGRgjdBHv5Xd7wEnagVu922Z1G+DrPFmThGy/P0V9XRejYWo9Veh6ZqMcTN63su6zIaKbpR2PXDYbZtnDtMwagjBPSuoEpITTAHe2nUENEeIS7VU6hjOhYsSEiB8OiGwkxYA7dA56dm62I2fm3cu1FwwaInN2/TxlR52UlUTIYlZQuPEFKt2ImiPsFis65rZDFyKNmHzo9NtokRsiUUqIy//6CC0xAgMBAAECggEAY84bZ0LEidEmvqZNFYovNZsN0Rsp+03CPtpHPSquzTMv+mvbGqvjrpCI8YBAHdKqFiCDKzC0u8dlsvzSaA6j2RqKBoMYGNLoXyVea4TpxRrWrL7igyydhN6Alqg5X7gCeikX1jyT6xPikSSxD3NvnudZCh8SdOD/8UcOrUecgFhMG3gNt88Sj5KSl7X63tx3Lo2yhFRrITNls8OYmO51XuMvtjeWYCoL+mZLRmmcv+2GX5RIxA2idKBTgtPS5/pmv9Dr2WvDBO6FKnTwunZD52H4VlpOVSIHu7RGuW0P4BZ26b3oSiyGTNjc0io+gEuzqEM9kut0azFMBX7QqmPDkQKBgQDU3iwkzfA27qHyJErVrGjsfioNPbpdhwilwMETGrH2YY9k9G5JUKzHSvXhRu00Kq0Zwe67611aQnPKlJhstJIV834BLGjELKMLsgzcH6uNpCOjzGruFyo+onIqw7HsVi54Fx/zBIt/+CDntfwR/liYiWACt69F0fxPOlS9w0iETQKBgQDBG8jPveakcCCAgyVw/jJLFCTmGvLBhRxfhRctp6+BROYwGyiA1xIXM5mQSXPtPhzr9rocMPrQjC/uIgGdPA0L8FHnGOyhUomrozmtqpHBaDPTIQ5hEz684olfGs0hMgqMi1U843bVAa7QWTBaOsjJwjYbuhhX06FGQDA+FwWOdQKBgQC7aNtOj+00P8ZerCsikgdSZwVY4QbKPw+S9vBFhJQ3yc74X+cVLyzezRVLACnoasY5zi8ETYTD6YuOSGMmGZggMV63Y8TdKjtmGZ0O0+cy7dbe6CMgeU6dFGIVWnO0EnAZFKrxRwgWY3t85REHOnn/2bqoydVLUEOl8UIYS+z1ZQKBgEWqW2CutrVeCiuypGBGJCi3xLQKxscP2XOQVRNQal1tWMnEEnGgpVMfd2f5R8wv20k5Ql0HQoTX/jafqAtsbZx//1wqvrtYRnhYM2e2oEyw5fll9IUK51hHNZ3M0nIGVo3rej20JdukdseNVYdHq8Y1vUV1dnDA1W69m2na7lM9AoGAb2P18uj0rOZKZVX0X+q7q1bQdABcqpX4/OzJJ/I5w1BoHAq3KxlJWOecwlSZjAWocn7J5ZaFnBgbyxZ5MTC0tOqkAp+aPWeE6e3D2k1GiArP0UvHIa3/kznpNj0kQyox0JQ/U9hCKaTc5Se5ei7yr+xIxRgvsufcw1SyOOowJeo=";
		
		// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAneh1YJCMml4f64b7njPNJmF5ZJNeTtOhwTzgKCGKCesatzlmOgKYle7RlnvJx4bPjrNsCCzIe+RPD7X3+ki7vZwkgztN36LEs7GMTCd9qJiG1hnz68ak+/ZYz+YD2Z1BFYs/KrAhu0EA9gipBIweWwOz7zkq5KQUI5ewkcO03rUeVGn0E/cnc0VgYumGzLIty6+p/xc4GoMGywwVVfHtZfF7gRSEL9F8CohShL7ia6CILVt5ITKyB3emS2ju9O9WI/VEkPdNkTYEYYghXXLT9FZwkysh3GQdUK4T8yebU+QybqMID4Jojur4UgxItG4AQoCeksIAJvabKvLQpRUBpQIDAQAB";

		// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

		// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String return_url = "http://localhost:8080/pay_over";

		// 签名方式
		public static String sign_type = "RSA2";
		
		// 字符编码格式
		public static String charset = "utf-8";
		
		// 支付宝网关
		public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
		
		// 支付宝网关
		public static String log_path = "C:\\";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}
