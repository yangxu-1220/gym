/**
 * 拍照上传图片
 */
var indexObj = {};

indexObj.tips = "没有检测到设备，请确保开启摄像头";

// begin 显示摄像头的录像，对象包含的属性描述了正在使用的浏览器，
//可以使用这些属性进行平台专用的配置
navigator.getUserMedia = navigator.getUserMedia ||
		navigator.webkitGetUserMedia ||
		navigator.mozGetUserMedia;

if (navigator.getUserMedia) {
	navigator.getUserMedia({ audio: true, video: { width: 800, height: 450 } },
	function(stream) {
		var video = document.getElementById("index__video");
		video.srcObject = stream;
		console.log("正在使用", stream)
		video.onloadedmetadata = function(e) {
			video.play();
		};
	},
	function(err) {
		console.log("出现了错误：" + err.name);
		document.getElementById("index__tips").innerHTML = indexObj.tips;
	}
);
} else {
	console.log("不支持使用浏览器！");
	document.getElementById("index__tips").innerHTML = indexObj.tips;
}
// end 显示摄像头的录像。


/**
 * 拍照上传按钮的事件响应
 */

indexObj.uploadImg = function(){
	//getContext("2d") 对象是内建的 HTML5 对象，拥有多种绘制路径、矩形、圆形、字符以及添加图像的方法
	var canvas = document.getElementById("index__canvas");
	var video = document.getElementById("index__video");
	if (0 == video.videoWidth) {
		document.getElementById("index__tips").innerHTML = indexObj.tips;
		return;
	}
	// 让canvas和拍摄框一样宽高。
	var w = video.videoWidth;
	var h = video.videoHeight;
	canvas.width = w;
	canvas.height = h;
	// 把video标签中的画面，画到canvas中。
	var ctx = canvas.getContext('2d');
	ctx.drawImage(video, 0, 0, w, h);
	// 把canvas中的图像转换成png图片文件的Base64字符串。
	var memberImage = canvas.toDataURL('image/png').split("base64,")[1];
	// 获得ID
	var memberId = document.getElementById("index__member-id").value;
	axios.post("/api/profile/upload", {"memberId":memberId, "memberImage":memberImage})
			.then(function(res){
				//var realPath=JSON.stringify(res.data.realPath);
				//alert(realPath);
				//document.getElementById("image").innerHTML="<img src=\""+realPath+"\">";
				console.log(res);
				alert("上传成功")
			}).catch(function(error){
				console.error(error);
			})
}
