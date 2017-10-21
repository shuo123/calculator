<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网贷计算器</title>
<script src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>

<body style="background-image:url(${pageContext.request.contextPath}/images/background.jpg)">
	<div style="height:10%; width:100%; float:left;" id="NameBlock">
		<font id="Name">网贷计算器</font>
	</div>
	<div style="height:90%; width:100%">
		<div id='frame1' style="float:left">
			<div id='inputBlock'>
				<form method="post" action="${pageContext.request.contextPath}/calculate">
					<input type="hidden" name="_method" value="POST" />
					<table width="350" border="0">
						<tr>
							<td><div align="right">
									<font>借出日期</font>
								</div></td>
							<td>
							<fmt:formatDate value="${c.date }" pattern="yyyy-MM-dd" var="d" />
							<input id="date" name="date" type="text"
								onClick="WdatePicker()" value='${d }' /></td>
							<td><div align="left"></div></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>借出金额</font>
								</div></td>
							<td><input id="money" name="money" type="number" value="${c.money }" /></td>
							<td><div align="left"></div></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>借出期限</font>
								</div></td>
							<td><input id="limit" name="limit" type="number" value="${c.limit }" /></td>
							<td></td>
						</tr>
						<td></td>
						<td><input id="limitIsDay" name="limitIsDay" type="radio"
							value="0" checked="checked" /><font>个月</font> <!-- <input id="date" name="limitIsDay" type="radio" value="1" /><font>天</font> -->
						</td>
						<td></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>利率</font>
								</div></td>
							<td><input id="rate" name="rate" type="number" value="${c.rate*100 }" /></td>
							<td><font>%</font></td>
						</tr>
						<td></td>
						<td>
						
						<input id="rateIsDay" name="rateIsDay" type="radio" value="0" checked="checked" /><font>年利率</font> 
						<input id="rateIsDay" name="rateIsDay" type="radio" value="1" <c:if test="${c.rateIsDay eq '1' }"> checked="checked" </c:if> /><font>日利率</font>
						</td>
						<td></td>
						</tr>
						<tr>
							<td><div align="right">
									<font>还款方式</font>
								</div></td>
							<td>
							<select name="method" id="method">
									<option value="monthInterestDueDebt" <c:if test="${c.method eq 'monthInterestDueDebt' }">selected</c:if>>月还息到期还本</option>
									<option value="alsoDuePrincipalAndInterest" <c:if test="${c.method eq 'alsoDuePrincipalAndInterest' }">selected</c:if>>到期还本期</option>
									<option value="equalPrincipalInterest" <c:if test="${c.method eq 'equalPrincipalInterest' }">selected</c:if>>等额本息</option>
									<option value="equalPrincipal" <c:if test="${c.method eq 'equalPrincipal' }">selected</c:if>>等额本金</option>
							</select>
							</td>
							<td><div align="left"></div></td>
						</tr>
						<tr>
							<td colspan="3"><div align="center">
									<input id="calculate" type="submit" value="计算"/></td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<div id='frame2' style="float:right;overflow-y:scroll">
			<table id="resultTable" width="100%" border="0">
				<tr>
					<td><div align="center">
							<font>期号</font>
						</div></td>
					<td><div align="center">
							<font>代收（含本金）</font>
						</div></td>
					<td><div align="center">
							<font>本金</font>
						</div></td>
					<td><div align="center">
							<font>利息</font>
						</div></td>
					<td><div align="center">
							<font>到期时间</font>
						</div></td>
				</tr>
				<c:forEach items="${requestScope.result }" var="r">
				<tr>
					<td><div align="center">
							<font>${r.num }</font>
						</div></td>
					<td><div align="center">
							<font>${r.total }</font>
						</div></td>
					<td><div align="center">
							<font>${r.principal }</font>
						</div></td>
					<td><div align="center">
							<font>${r.interest }</font>
						</div></td>
					<td><div align="center">
							<font>${r.date }</font>
						</div></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</body>
</html>
