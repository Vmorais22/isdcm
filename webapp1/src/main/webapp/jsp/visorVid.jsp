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
  <link href="//vjs.zencdn.net/7.10.2/video-js.min.css" rel="stylesheet">
</head>
<body>
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
    <h1>Status <%= request.getSession().getAttribute("status").toString() %> </h1>
    <h1><%= title %> </h1>
    <h1>Por: <%= author %> </h1>
    <h1><%= description %> </h1>
    <h1>Visto: <%= views %> </h1>
    <h1><%= currentLink %> </h1>
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