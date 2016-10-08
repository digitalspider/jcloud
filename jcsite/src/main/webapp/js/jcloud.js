function getLinks(ctx, tags, maxRows, templateEleName, resultEleName) {
	var formURL = ctx+"/rest/link";
	getAjaxContent(formURL, tags, maxRows, templateEleName, resultEleName);
}

function getBlogs(ctx, tags, maxRows, templateEleName, resultEleName) {
	var formURL = ctx+"/rest/blog";
	getAjaxContent(formURL, tags, maxRows, templateEleName, resultEleName);
}

function getAjaxContent(ajaxURL, tags, maxRows, templateEleName, resultEleName) {
	if (tags!=null) {
		ajaxURL = ajaxURL + "/"+tags;
	}
	if (maxRows!=null) {
		ajaxURL = ajaxURL + "/"+maxRows;
	}
	if (resultEleName==null) {
		resultEleName = "#links";
	}
	$.post(ajaxURL, null, function (data) {
		var ele = $(resultEleName);
		if (data.length>0) {
			//var backup = $.extend( true, {}, ele );
			ele.empty(); // remove old values
			var template = $(templateEleName).html();
			Mustache.parse(template);   // optional, speeds up future uses
			var rendered = Mustache.render(template, {content: data});
			ele.html(rendered);
			/*
			$.each(data, function(i, blog) {
				ele.append($("<div></div>").attr("class", "col-xs-12 col-sm-6 col-md-6 col-lg-3 col-xl-3")
					.append($("<div></div>").attr("class", "tm-content-box")
						.append($("<h4></h4>").attr("class", "tm-margin-b-20 tm-gold-text").text(blog.title))
						.append($("<p></p>").attr("class", "tm-margin-b-20").html(blog.description))
						.append($("<a></a>").attr("href", blog.link).attr("class","tm-btn text-uppercase").text("Read more"))
					)
				);
			});
			*/
		}
	}, 'json');
}