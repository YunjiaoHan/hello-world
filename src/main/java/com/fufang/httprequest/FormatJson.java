package com.fufang.httprequest;

public class FormatJson {

	public static void printJson(String jsonStr){
		System.out.println("参数 :\n" + formatJson(jsonStr));
	}

	/**
	 * 格式化
	 * @param jsonStr
	 * @return
	 */
	public static String formatJson(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr)) return "";
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);

			//遇到{ [换行，且下一行缩进
			switch (current) {
			case '{':
			case '[':
				sb.append(current);
				sb.append('\n');
				indent++;
				addIndentBlank(sb, indent);
				break;

				//遇到} ]换行，当前行缩进
			case '}':
			case ']':
				sb.append('\n');
				indent--;
				addIndentBlank(sb, indent);
				sb.append(current);
				break;

				//遇到,换行
			case ',':
				sb.append(current);
				if (last != '\\') {
					sb.append('\n');
					addIndentBlank(sb, indent);
				}
				break;
			default:
				sb.append(current);
			}
		}

		return sb.toString();
	}

	/**
	 * 添加space
	 * @param sb
	 * @param indent
	 */
	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append('\t');
		}
	}

/*	public static void main(String[] args){

		String jsonStr = "{\"key\":\"61300\",\"data\":[{\"commonName\":\"通用名004\",\"dosage\":\"剂型004\",\"validDate\":\"2013-12-13\",\"is_kcbxs\":\"是\",\"batchNum\":\"批号004\",\"zhongbao\":\"4\",\"createPerson\":\"操作员004\",\"licenseNum\":\"国药准字004\",\"productDate\":\"2012-12-12\",\"spec\":\"规格004\",\"unit\":\"单位004\",\"matcode\":\"药品编码004\",\"price\":\"14.4\",\"sellState\":\"y\",\"name\":\"名称004\",\"prodPlace\":\"厂址004\",\"manufName\":\"厂家004\",\"storeNum\":\"100\",\"barcode\":\"药品条码004\",\"retailPrice\":\"14.4\",\"manufNameAbbr\":\"北京\"}]}";
		FormatJson.printJson(jsonStr);

	}*/

}
