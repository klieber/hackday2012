<%@ page session="true" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
  <body>
    <div id="primaryContent">
      <tiles:insertAttribute name="content" />
    </div>
  </body>
</html>