<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	String currentLink = request.getSession().getAttribute("currentLink").toString();
	currentLink = currentLink.substring(0, currentLink.length() - 1);
	String title = request.getSession().getAttribute("currentVideoTitle").toString();
	String author = request.getSession().getAttribute("currentVideoAuthor").toString();
	String description = request.getSession().getAttribute("currentVideoDescription").toString();
	String views = request.getSession().getAttribute("currentVideoViews").toString();
%>
<html>
<head>
	<title>Reproducción del video</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/webapp1/css/style.css" >
	<link href="//vjs.zencdn.net/7.10.2/video-js.min.css" rel="stylesheet">
</head>
<body>
	<div class="card">
    	<div class="card-body">
        	<video
          	id="vid1"
          	class="video-js vjs-default-skin vjs-big-play-centered vjs-using-native-controls"
          	style="margin:auto;"
          	controls
          	width="1280" height="720"
          	autoplay
          	controls preload="auto" width="640" height="360"
          	data-setup='{ "techOrder": ["youtube", "html5"], "youtube": {"ytControls": 2}}'
        	>
            	<source id="video" src=<%= currentLink %> type='video/youtube'>
        	</video>
   	 
        	<div class="card">
            	<div class="card-body">
                	<h3><%= title %></h3>
                	<h3><small class="text-muted"><%= views %> visualizaciones · Por <%= author %></small></h3>
                	<!--<h1>Status <%= request.getSession().getAttribute("status").toString() %> </h1>-->
                	<h5><%= description %> </h5>
                	<!--<h1><%= currentLink %> </h1>-->
            	</div>
        	</div>
        	<br>
        	<a href="listadoVid.jsp" class="btn btn-secondary">Volver</a>
    	</div>
	</div>
    
	<script>
    	var vid = document.getElementById("vid1");
    	vid.onended = function() {
        	window.location.href = 'visorVid.jsp?link_url=&index=';
    	};
	</script>
	<script src="//vjs.zencdn.net/7.10.2/video.min.js"></script>
	<script src="../js/Youtube.js"></script>
</body>
</html>