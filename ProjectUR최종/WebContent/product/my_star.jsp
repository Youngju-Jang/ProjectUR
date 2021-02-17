<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
											<c:choose>
                     						<c:when test="${0 <= my_star && my_star < 0.5}">
                     						<i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${0.5 <= my_star && my_star < 1}">
                     						<i class="fa fa-star-half"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${1 <= my_star && my_star < 1.5}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${1.5 <= my_star && my_star < 2}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star-half"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${2 <= my_star && my_star < 2.5}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${2.5 <= my_star && my_star < 3}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-half"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${3 <= my_star && my_star < 3.5}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${3.5 <= my_star && my_star < 4 }">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-half"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${4 <= my_star && my_star < 4.5 }">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${4.5 <= my_star && my_star < 5}">
                     						 <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-half"></i>
                     						</c:when>
                     						<c:when test="${my_star == 5}">
                     						 <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
                     						</c:when>
                     					</c:choose>