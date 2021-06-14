<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Employee Management Application</title>
	<!-- Icons font CSS-->
	    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all"/>
	    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all"/>
	<!-- Font special for pages-->
	 	<link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet"/>
   	<!-- Vendor CSS-->
	    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all" />
	    <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all"/>
	<!-- Main CSS-->
   		 <link href="css/main.css" rel="stylesheet" media="all" />    
   		 
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato;padding:16px">
			<div>
				<a style="color:white; text-decoration:none;font-size:18px;margin-left:10px" href="#" class="navbar-brand"> Employee Management App </a>
			</div>
		</nav>
	</header>
	<br>

	<div class="page-wrapper p-t-100 p-b-85 font-poppins">
      <div class="wrapper wrapper--w960">
        <div class="card card-4">
          <div class="card-body">
          		<c:if test="${e != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${e == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2 class="title">
						<c:if test="${e != null}">
            			Cap nhat thong tin nhan vien 
            		</c:if>
						<c:if test="${e == null}">
            			Them moi nhan vien
            		</c:if>
					</h2>
				</caption>
				<c:if test="${e != null}">
					<input type="hidden" name="id" value="<c:out value='${e.id}' />" />
				</c:if>
           
              <div class="row row-space">
                <div class="col-2">
                  <div class="input-group">
                    <label class="label">Ma nhan vien</label>
                    <input  class="input--style-4" type="text" value="<c:out value='${e.code}' />" class="form-control" name="code" required>
                  </div>
                </div>
                <div class="col-2">
                  <div class="input-group">
                    <label class="label">Ten nhan vien</label>
                    <input class="input--style-4" type="text" value="<c:out value='${e.name}' />" class="form-control" name="name" required> 
                  </div>
                </div>
              </div>
              <div class="row row-space">
                <div class="col-2">
                  <div class="input-group">
                    <label class="label">Ngay sinh</label>
                    <div class="input-group-icon">
                      <input
                        class="input--style-4 js-datepicker"
                        type="text"
                        name="birthday"
                        value="<c:out value='${e.birthday}'/>"
                        required
                      />
                      <i
                        class="
                          zmdi zmdi-calendar-note
                          input-icon
                          js-btn-calendar
                        "
                      ></i>
                    </div>
                  </div>
                </div>
                <div class="col-2">
                  <div class="input-group">
                    <label class="label">Gioi tinh</label>
                    <div class="p-t-10">
                      <label class="radio-container m-r-45"
                        >Nam
                        <input type="radio" checked="checked" name="gender" ${e.gender==1?"checked":""}/>
                        <span class="checkmark"></span>
                      </label>
                      <label class="radio-container"
                        >Nu
                        <input type="radio" name="gender"  ${e.gender==0?"checked":""}/>
                        <span class="checkmark"></span>
                      </label>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row row-space">
                <div class="col-2">
                  <div class="input-group">
                    <label class="label">Dia chi</label>
                    <input class="input--style-4" type="text" name="address" value="<c:out value='${e.address}'/>"/>
                  </div>
                </div>
                <div class="col-2">
                  <div class="input-group">
                    <label class="label">Chuc vu</label>
                    <div class="rs-select2 js-select-simple select--no-search">
                      <select name="positionId" required>
		                    <c:if test="${e != null}">
		            			<option disabled="disabled" selected="selected">
                        		 ${e.positionId.name}</option>
		            		</c:if>
							<c:if test="${e == null}">
		            			<option disabled="disabled" selected="selected">
                          		Chon chuc vu</option>
		            		</c:if>
                        <c:forEach var="pos" items="${listPos}">
                        	<option value="<c:out value='${pos.id}'/>">${pos.name}</option>
                        </c:forEach>
                      </select>
                      <div class="select-dropdown"></div>
                    </div>
                  </div>
                </div>
              <div class="p-t-15">
                <button class="btn btn--radius-2 btn--tomato" type="submit">
                  Luu lai
                </button>
                <a style="border: 1px solid tomato; background:white;color:tomato; text-decoration:none" class="btn btn--radius-2" href="<%=request.getContextPath()%>/list"
					class="nav-link">Quay lai</a>
                
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- Jquery JS-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/datepicker/moment.min.js"></script>
    <script src="vendor/datepicker/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="js/global.js"></script>
</body>
</html>
