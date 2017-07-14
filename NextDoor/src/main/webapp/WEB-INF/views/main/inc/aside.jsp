<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
 <!--sidebar start-->
       <aside>
          <div id="sidebar"  class="nav-collapse " style="overflow: visible;">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              	  <p class="centered"><img src="resources/main/assets/img/user.png" class="img-circle" width="90"></p>
              	  <h5 class="centered"><sec:authentication property="principal.username"/></h5>
              	  
                  <li class="mt">
                      <a href="<%=request.getContextPath()%>/projectList.htm?workspace_no=${workspace_no}" >
                          <i class="fa fa-tasks"></i>
                          <span>프로젝트</span>
                      </a>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-tasks"></i>
                          <span>업무 목록</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="<%=request.getContextPath()%>/task.htm?project_no=${project_no}">전체 업무</a></li>
                          <li><a  href="<%=request.getContextPath()%>/personaltask.htm?project_no=${project_no}">개인 업무</a></li>
                      </ul>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-calendar"></i>
                          <span>일정 목록</span>
                      </a>
                      <ul class="sub">
                          <li><a href="<%=request.getContextPath()%>/calendar.htm">달력</a></li>
                          <li><a href="<%=request.getContextPath()%>/chartlist.htm">차트</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="<%=request.getContextPath()%>/listfile.htm" >
                          <i class="fa fa-cogs"></i>
                          <span>파일 목록</span>
                      </a>
                  </li>
				 <li class="sub-menu">
                      <a href="<%=request.getContextPath()%>/chat.htm" >
                          <i class=" fa fa-desktop"></i>
                          <span>채팅</span>
                      </a>
                 </li>
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->