<!DOCTYPE html>
<html>
<head>
  <link href="//vjs.zencdn.net/7.10.2/video-js.min.css" rel="stylesheet">
</head>
<body>
  <video
    id="vid1"
    class="video-js vjs-default-skin vjs-big-play-centered"
    style="margin:auto;"
    controls
    
    controls preload="auto" width="640" height="360"
    data-setup='{ "techOrder": ["youtube", "html5"]}'
  >
      <source id="video" src="" type='video/youtube'>
  </video>
  <script>
      var params = (new URL(document.location)).searchParams;
      var url = params.get('link_url').slice(0,-1);
      var source = document.querySelector("video > source");
      source.src = url;
  </script>

  <script src="//vjs.zencdn.net/7.10.2/video.min.js"></script>
  <script src="../js/Youtube.js"></script>
</body>
</html>