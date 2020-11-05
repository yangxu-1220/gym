/**
 * 封装用户信息弹出窗口
 */

layui.use(['layer','jquery'],function(){
	var layer=layui.layer;
	var $=layui.jquery;
	$(document).on('click','#userInformation',function(){
		layer.open({				
			type:2,
			title:"修改用户信息",
			area:['400px','500px'],
			//btn:['确定','取消'],
			content:['/user/get_detail_user','yes'],
			yes:function(index,layero){
				var body=layer.getChildFrame('body',index);
				var AddIframe=window[layero.find('iframe')[0]['name']];
				var index=parent.layer.getFrameIndex(window.name);
				//$("#userType").empty();
				parent.layer.close(index);
				parent.location.reload();									
			}
		});
	});
	
	$(document).on('click','#user_name',function(){
		layer.open({				
			type:2,
			title:"修改用户信息",
			area:['400px','500px'],
			//btn:['确定','取消'],
			content:['/user/get_detail_user','yes'],
			yes:function(index,layero){
				var body=layer.getChildFrame('body',index);
				var AddIframe=window[layero.find('iframe')[0]['name']];
				var index=parent.layer.getFrameIndex(window.name);
				//$("#userType").empty();
				parent.layer.close(index);
				parent.location.reload();									
			}
		});
	});
	
	$(document).on('click','#user_img',function(){
		layer.open({				
			type:2,
			title:"修改用户信息",
			area:['400px','500px'],
			//btn:['确定','取消'],
			content:['/user/get_detail_user','yes'],
			yes:function(index,layero){
				var body=layer.getChildFrame('body',index);
				var AddIframe=window[layero.find('iframe')[0]['name']];
				var index=parent.layer.getFrameIndex(window.name);
				//$("#userType").empty();
				parent.layer.close(index);
				parent.location.reload();									
			}
		});
	});
});
