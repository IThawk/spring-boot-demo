package com.gupaoedu.security.codec;

/**
 * 移动端接口常量
 *
 */
public class CodecToolKit {
	/**
	 * 请求授权码加密静态Key
	 */
	private static final String ENC_CODE = "@!#^^!&@^@#&()*2";

	/**
	 * 生成授权码
	 * @param params
	 * @return
	 */
	public static String genEnc(String params){
		return MD5.encode(ENC_CODE + params);
	}
}
