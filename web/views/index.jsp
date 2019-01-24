<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>MegaApp</title>
    <link type="text/css" rel="stylesheet" href="css/site.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  </head>
  <body>
    <div class="welcome">
      <h2>Welcome to MegaApp!</h2>
    </div>
    <div class="input">
      <h3>Type here whatever you want to find on stackoverflow</h3>
      <form method="post">
        <p class="input-section">
          <label>
            Title: <input type="text" name="title"/>
          </label>
          <label>
            Include tags: <input type="text" name="include"/>
          </label>
          <label>
            Exclude tags: <input type="text" name="exclude"/>
          </label>
        </p>
        <p class="input-section">
          <label>
            Page number: <input type="number" name="pagenum" value="1"/>
          </label>
          <label>
            Page size: <input type="number" name="pagesize" value="30"/>
          </label>
        </p>
        <p class="input-section">
          <label>
            From:<input type="date" name="from"/>
          </label>
          <label>
            To:<input type="date" name="to"/>
          </label>
        </p>
        <p class="input-section">
          <label>Order
              <select name="order">
                  <option value="desc">desc</option>
                  <option value="asc">asc</option>
              </select>
          </label>
          <label>Sort by
              <select name="sort">
                  <option value="activity">activity</option>
                  <option value="votes">votes</option>
                  <option value="creation">creation</option>
                  <option value="relevance">relevance</option>
              </select>
          </label>
        </p>
        <input type="submit" value="search"/>
      </form>
    </div>
    <c:if test='${not empty requestScope.get("error")}'>
        <c:choose>
            <c:when test='${requestScope.get("error").isCritical()}'>
                <div class="error critical">
            </c:when>
            <c:otherwise>
                <div class="error">
            </c:otherwise>
        </c:choose>
                    Error: ${requestScope.get("error").getMessage()}
        </div>
    </c:if>
    <div>
        <c:forEach var="question" items='${requestScope.get("result")}'>
            <c:choose>
                <c:when test="${question.isAnswered()}">
                    <div class="question answered">
                </c:when>
                <c:otherwise>
                    <div class="question">
                </c:otherwise>
            </c:choose>
                <div class="left">
                    <div class="photo">
                        <a class="profile-picture" href="${question.getOwnerLink()}">
                            <img src="${question.getPicture()}"/>
                        </a>
                    </div>
                    <div class="reputation">
                            <span class="fa fa-star"> ${question.getRep()}
                    </div>
                </div>
                <div class="right">
                    <div class="question-header">
                            ${question.getOwnerName()} asked at ${question.getCreated()}
                    </div>
                    <div class="question-title">
                        <a href="${question.getLink()}">
                                ${question.getTitle()}
                        </a>
                    </div>
                    <div class="question-tags">
                        <c:forEach var="tag" items='${question.getTags()}'>
                            <div class="tag">${tag}</div>
                        </c:forEach>
                    </div>
                    <div class="question-footer">
                        <div><span class="fa fa-comment"></span> ${question.getAnswerCount()}</div>
                        <div><span class="fa fa-eye"></span> ${question.getViewCount()}</div>
                        <div><span class="fa fa-trophy"></span> ${question.getScore()}</div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
  </body>
</html>
