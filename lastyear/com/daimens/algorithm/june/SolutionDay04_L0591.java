package com.daimens.algorithm.june;

import java.util.Stack;

/**
 * 
 * @author DemonSong
 * 
 *         591. Tag Validator
 * 
 *         Given a string representing a code snippet, you need to implement a
 *         tag validator to parse the code and return whether it is valid. A
 *         code snippet is valid if all the following rules hold:
 * 
 *         The code must be wrapped in a valid closed tag. Otherwise, the code
 *         is invalid. A closed tag (not necessarily valid) has exactly the
 *         following format : <TAG_NAME>TAG_CONTENT</TAG_NAME>. Among them,
 *         <TAG_NAME> is the start tag, and </TAG_NAME> is the end tag. The
 *         TAG_NAME in start and end tags should be the same. A closed tag is
 *         valid if and only if the TAG_NAME and TAG_CONTENT are valid. A valid
 *         TAG_NAME only contain upper-case letters, and has length in range
 *         [1,9]. Otherwise, the TAG_NAME is invalid. A valid TAG_CONTENT may
 *         contain other valid closed tags, cdata and any characters (see note1)
 *         EXCEPT unmatched <, unmatched start and end tag, and unmatched or
 *         closed tags with invalid TAG_NAME. Otherwise, the TAG_CONTENT is
 *         invalid. A start tag is unmatched if no end tag exists with the same
 *         TAG_NAME, and vice versa. However, you also need to consider the
 *         issue of unbalanced when tags are nested. A < is unmatched if you
 *         cannot find a subsequent >. And when you find a < or </, all the
 *         subsequent characters until the next > should be parsed as TAG_NAME
 *         (not necessarily valid). The cdata has the following format :
 *         <![CDATA[CDATA_CONTENT]]>. The range of CDATA_CONTENT is defined as
 *         the characters between <![CDATA[ and the first subsequent ]]>.
 *         CDATA_CONTENT may contain any characters. The function of cdata is to
 *         forbid the validator to parse CDATA_CONTENT, so even it has some
 *         characters that can be parsed as tag (no matter valid or invalid),
 *         you should treat it as regular characters. Valid Code Examples:
 *         Input: "<DIV>This is the first line <![CDATA[<div>]]></DIV>"
 * 
 *         Output: True
 * 
 *         Explanation:
 * 
 *         The code is wrapped in a closed tag : <DIV> and </DIV>.
 * 
 *         The TAG_NAME is valid, the TAG_CONTENT consists of some characters
 *         and cdata.
 * 
 *         Although CDATA_CONTENT has unmatched start tag with invalid TAG_NAME,
 *         it should be considered as plain text, not parsed as tag.
 * 
 *         So TAG_CONTENT is valid, and then the code is valid. Thus return
 *         true.
 * 
 * 
 *         Input: "<DIV>>> ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"
 * 
 *         Output: True
 * 
 *         Explanation:
 * 
 *         We first separate the code into : start_tag|tag_content|end_tag.
 * 
 *         start_tag -> "<DIV>"
 * 
 *         end_tag -> "</DIV>"
 * 
 *         tag_content could also be separated into : text1|cdata|text2.
 * 
 *         text1 -> ">> ![cdata[]] "
 * 
 *         cdata -> "<![CDATA[<div>]>]]>", where the CDATA_CONTENT is "<div>]>"
 * 
 *         text2 -> "]]>>]"
 * 
 * 
 *         The reason why start_tag is NOT "<DIV>>>" is because of the rule 6.
 *         The reason why cdata is NOT "<![CDATA[<div>]>]]>]]>" is because of
 *         the rule 7. Invalid Code Examples: Input: "<A> <B> </A> </B>" Output:
 *         False Explanation: Unbalanced. If "<A>" is closed, then "<B>" must be
 *         unmatched, and vice versa.
 * 
 *         Input: "<DIV> div tag is not closed <DIV>" Output: False
 * 
 *         Input: "<DIV> unmatched < </DIV>" Output: False
 * 
 *         Input: "<DIV> closed tags with invalid tag name <b>123</b> </DIV>"
 *         Output: False
 * 
 *         Input: "<DIV> unmatched tags with invalid tag name </1234567890> and
 *         <CDATA[[]]> </DIV>" Output: False
 * 
 *         Input: "<DIV> unmatched start tag <B> and unmatched end tag </C>
 *         </DIV>" Output: False Note: For simplicity, you could assume the
 *         input code (including the any characters mentioned above) only
 *         contain letters, digits, '<','>','/','!','[',']' and ' '.
 *
 */
public class SolutionDay04_L0591 {

	// public boolean isValid(String code) {
	// if (code.length() == 0) return false;
	// char[] cs = code.toCharArray();
	// if (cs[0] != '<') return false;
	//
	// String beginTag = parseBeginTag(code);
	// String endTag = parseEndTag(code);
	//
	// if (!isTagValid(beginTag, endTag)) return false;
	//
	// //检测内容
	// int i = beginTag.length() + 2, j = code.length() - 1 - endTag.length() -
	// 2;
	// String checkCode = code.substring(i, j+1);
	//
	// //检测cddata
	// String pp = parseCDDATA(checkCode);
	//
	// //find 所有Tag
	// List<String> tags = new ArrayList<>();
	// char[] checks = checkCode.toCharArray();
	//
	// StringBuilder sb = new StringBuilder();
	// for (int k = 0; k < checks.length; k++){
	// if (checks[k] == '<'){
	// sb = new StringBuilder();
	// }else if (checks[k] != '>'){
	// sb.append(checks[k]);
	// }
	// else if (checks[k] == '>'){
	// tags.add(sb.toString());
	// }
	// }
	//
	// if (tags.size() % 2 != 0) return false;
	//
	// int lf = 0, rt = tags.size()-1;
	// while (lf <= rt){
	// if (!isTagValid(tags.get(lf), tags.get(rt))) return false;
	// lf ++;
	// rt --;
	// }
	//
	// return true;
	// }
	//
	// private String parseCDDATA(String code){
	// String start = "<![CDATA[";
	// String end = "]]>";
	//
	// int i = code.indexOf(start);
	// int j = code.indexOf(end);
	//
	// if (i == -1 || j == -1) return code;
	//
	//
	//
	// return "";
	// }
	//
	//
	// private String parseBeginTag(String code){
	// int i = 0;
	// while (i < code.length() && code.charAt(i) != '>') i++;
	// if (i == code.length()) return "";
	// return code.substring(1, i);
	// }
	//
	//
	// private String parseEndTag(String code){
	// int j = code.length()-1;
	// while (j >= 0 && code.charAt(j) != '<') j--;
	// if (j == -1) return "";
	// return code.substring(j+1,code.length()-1);
	// }
	//
	// private boolean isTagValid(String beginTag,String endTag){
	// if (beginTag.isEmpty() || endTag.isEmpty()) return false;
	//
	// for (int i = 0; i < beginTag.length(); i++){
	// if (Character.isLowerCase(beginTag.charAt(i))) return false;
	// }
	//
	// char[] ends = endTag.toCharArray();
	// char[] begins = beginTag.toCharArray();
	// if (ends[0] != '/' && beginTag.length() != endTag.length()-1) return
	// false;
	//
	// for (int i = 1; i < endTag.length(); i++){
	// if (Character.isLowerCase(ends[i]) && ends[i] != begins[i]) return false;
	// }
	// return true;
	// }

	// int pos = 0, len = 0;
	// char[] cs;
	// public boolean isValid(String code) {
	// cs = code.toCharArray();
	// len = cs.length;
	// try{
	// if(!closedTag()) return false;
	// if(pos < len) return false;
	// return true;
	// }catch(Throwable t){
	// return false;
	// }
	// }
	//
	// private boolean closedTag(){
	// if (! (pos < len && cs[pos] == '<')) return false;
	// pos ++;
	//
	// StringBuilder sb = new StringBuilder();
	// while (pos < len && cs[pos] != '>'){
	// sb.append(cs[pos++]);
	// }
	//
	// String tagName = sb.toString();
	// if (!isTagName(tagName)) return false;
	// pos++;
	//
	// if (!content()) return false;
	//
	// if (!(pos < len && cs[pos] == '<')) return false;
	// pos++;
	// if (!(pos < len && cs[pos] == '/')) return false;
	// pos++;
	//
	// sb = new StringBuilder();
	// while (pos < len && cs[pos] != '>'){
	// sb.append(cs[pos++]);
	// }
	// String tagName2 = sb.toString();
	// if (!(isTagName(tagName2) && tagName.equals(tagName2))) return false;
	//
	// if (! (pos < len && cs[pos] == '>')) return false;
	// pos++;
	//
	// return true;
	// }
	//
	// private boolean isTagName(String tagName){
	// if (!(tagName.length() >= 1 && tagName.length() <= 9)) return false;
	// for (char c : tagName.toCharArray()){
	// if (! (c >= 'A' && c <= 'Z')) return false;
	// }
	// return true;
	// }
	//
	// private boolean content(){
	// while (pos < len){
	// while (pos < len && cs[pos] != '<') pos++;
	// if (pos == len) return true;
	// if (cs[pos + 1] == '/') return true;
	// if (cs[pos + 1] == '!'){
	// if (!cdata()) return false;
	// }else{
	// if (!closedTag()) return false;
	// }
	// }
	// return true;
	// }
	//
	// private boolean cdata(){
	// String start = "<![CDATA[", end = "]]>";
	// for (char c : start.toCharArray()){
	// if (!(pos < len && cs[pos] == c)) return false;
	// pos ++;
	// }
	//
	// while (pos + 2 < len && !(cs[pos] == ']' && cs[pos + 1] == ']' && cs[pos
	// + 2] == '>')){
	// pos ++;
	// }
	//
	// for (char c : end.toCharArray()){
	// if (!(pos < len && cs[pos] == c)) return false;
	// pos ++;
	// }
	// return true;
	// }

	public boolean isValid(String code) {
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < code.length();) {
			if (i > 0 && stack.isEmpty())
				return false;
			if (code.startsWith("<![CDATA[", i)) {
				int j = i + 9;
				i = code.indexOf("]]>", j);
				if (i < 0)
					return false;
				i += 3;
			} else if (code.startsWith("</", i)) {
				int j = i + 2;
				i = code.indexOf('>', j);
				if (i < 0 || i == j || i - j > 9)
					return false;
				for (int k = j; k < i; k++) {
					if (!Character.isUpperCase(code.charAt(k)))
						return false;
				}
				String s = code.substring(j, i++);
				if (stack.isEmpty() || !stack.pop().equals(s))
					return false;
			} else if (code.startsWith("<", i)) {
				int j = i + 1;
				i = code.indexOf('>', j);
				if (i < 0 || i == j || i - j > 9)
					return false;
				for (int k = j; k < i; k++) {
					if (!Character.isUpperCase(code.charAt(k)))
						return false;
				}
				String s = code.substring(j, i++);
				stack.push(s);
			} else {
				i++;
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		SolutionDay04_L0591 day = new SolutionDay04_L0591();
		String code = "<DIV>This is the first line <![CDATA[<div>]]></DIV><DIV>sjaja</DIV>";
		System.out.println(day.isValid(code));
	}
}
