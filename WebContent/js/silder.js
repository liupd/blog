var obj=null;
var As=document.getElementById('topnav').getElementsByTagName('a');
obj = As[0];
for(i=1;i<As.length;i++){if(window.location.href.indexOf(As[i].href)>=0)
obj=As[i];}
if(window.location.href.indexOf("show.htm")>=0){
	obj=As[1]
}
obj.id='topnav_current'