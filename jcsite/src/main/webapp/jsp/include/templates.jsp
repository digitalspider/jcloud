<script id="link-template" type="x-tmpl-mustache">
{{#content}}
	<li><a href="{{link}}" class="tm-text-link">{{title}}</a></li>
{{/content}}
</script>

<script id="link-footer-template" type="x-tmpl-mustache">
{{#content}}
	<li><a href="{{link}}" class="tm-footer-link">{{title}}</a></li>
{{/content}}
</script>

<script id="blog-template" type="x-tmpl-mustache">
{{#content}}
<div class="col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">
	<div class="tm-content-box">
		<h4 class="tm-margin-b-20 tm-gold-text">{{title}}</h4>
		<p class="tm-margin-b-20">{{{description}}}</p>
		<a href="{{link}}" class="tm-btn text-uppercase">Read more</a>
	</div>
</div>
{{/content}}
</script>

<script id="related-template" type="x-tmpl-mustache">
{{#content}}
<div class="media tm-related-post">
  <div class="media-left media-middle">
	<a href="{{link}}">
	  <img class="media-object" src="{{imgpath}}" alt="{{alttext}}">
	</a>
  </div>
  <div class="media-body">
	<a href="{{link}}"><h4 class="media-heading tm-gold-text tm-margin-b-15">{{title}}</h4></a>
	<p class="tm-small-font tm-media-description">{{{description}}}</p>
  </div>
</div>
{{/content}}
</script>