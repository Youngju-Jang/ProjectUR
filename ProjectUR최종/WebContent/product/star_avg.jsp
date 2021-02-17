<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
											<c:choose>
                     						<c:when test="${0 <= star_avg && star_avg < 0.5}">
                     						<i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${0.5 <= star_avg && star_avg < 1}">
                     						<i class="fa fa-star-half"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${1 <= star_avg && star_avg < 1.5}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${1.5 <= star_avg && star_avg < 2}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star-half"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${2 <= star_avg && star_avg < 2.5}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${2.5 <= star_avg && star_avg < 3}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-half"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${3 <= star_avg && star_avg < 3.5}">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-o"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${3.5 <= star_avg && star_avg < 4 }">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-half"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${4 <= star_avg && star_avg < 4.5 }">
                     						<i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-o"></i>
                     						</c:when>
                     						<c:when test="${4.5 <= star_avg && star_avg < 5}">
                     						 <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star-half"></i>
                     						</c:when>
                     						<c:when test="${star_avg == 5}">
                     						 <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
	                                         <i class="fa fa-star"></i>
                     						</c:when>
                     					</c:choose>