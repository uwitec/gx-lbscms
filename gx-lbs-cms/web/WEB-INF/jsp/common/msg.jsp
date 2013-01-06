<%@ page contentType="text/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%out.clear();out.flush(); %>
{"msg":"<s:property value="%{getText(exception.errorCode)}"/>","success":true}