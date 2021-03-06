<%--
** Copyright (c) 2008, 2009, 2010
**      The Regents of the Tsinghua University, PRC.  All rights reserved.
**
** Redistribution and use in source and binary forms, with or without  modification, are permitted provided that the following conditions are met:
** 1. Redistributions of source code must retain the above copyright  notice, this list of conditions and the following disclaimer.
** 2. Redistributions in binary form must reproduce the above copyright  notice, this list of conditions and the following disclaimer in the  documentation and/or other materials provided with the distribution.
** 3. All advertising materials mentioning features or use of this software  must display the following acknowledgement:
**  This product includes software (iNetBoss) developed by Tsinghua University, PRC and its contributors.
** THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
** ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
** IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
** ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
** FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
** DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
** OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
** HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
** LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
** OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
** SUCH DAMAGE.
*
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN " "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>TopN排序</title>
    <link rel='StyleSheet' href="css/topoInit.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/ext-all.css"/>
    <link rel="stylesheet" type="text/css" href="flex/css/Style.css" />
    <script type="text/javascript">
    	var linkStore = "";
    </script>
    <script type="text/javascript" src="flex/js/FusionCharts.js"></script>
    <script type="text/javascript" src="js/ext-base.js"></script>
    <script type="text/javascript" src="js/ext-all.js"></script>
  <script>
Ext.onReady(function() {
	var reader = new Ext.data.JsonReader({
				root : 'totalResult',
				fields : ['resultData','picName']
			});
	var topNStore = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : 'json/UserPortStatistic.do'
						}),
				// 创建JsonReader读取router记录
				reader : reader
			});
	topNStore.load();
   
    var trafficintable = new Array();
	var trafficouttable = new Array();
	var trafficalltable = new Array();
	var othertable = new Array();
	
	var typeName = new Array();	
	var userName = new Array();
	var userValue = new Array();
	
	topNStore.on('load',function(){   
 	var PicName = topNStore.getAt(0).data.picName;
 	var temp = topNStore.getAt(0).data.resultData;
    for (i=0;i<temp.length;i++){
	  	  typeName[i] = temp[i].typeName;      
	      userName[i] = temp[i].userName;
	      userValue[i] = temp[i].userValue;
	      
	      var tabledata = new Array();
	      tabledata.push(userName[i],userValue[i]);
	      if (typeName[i]=="port80"){
	      	 trafficalltable.push(tabledata);
	      }else if(typeName[i] == "port18600"){
	      	 trafficintable.push(tabledata);
	      }else if (typeName[i] == "port16703"){
	         trafficouttable.push(tabledata);
	      }else if (typeName[i] == "portother"){
	         othertable.push(tabledata);
	      }
     }
	
	var trafficallstore = new Ext.data.SimpleStore({
        fields: [ 
           {name: 'userName'},
           {name: 'userValue'}
        ]
    });
    trafficallstore.loadData(trafficalltable);
    
	var trafficinstore = new Ext.data.SimpleStore({
        fields: [ 
           {name: 'userName'},
           {name: 'userValue'}
        ]
    });
    trafficinstore.loadData(trafficintable);
    
	var trafficoutstore = new Ext.data.SimpleStore({
        fields: [ 
           {name: 'userName'},
           {name: 'userValue'}
        ]
    });
    trafficoutstore.loadData(trafficouttable);
    
	var otherstore = new Ext.data.SimpleStore({
        fields: [ 
           {name: 'userName'},
           {name: 'userValue'}
        ]
    });
    otherstore.loadData(othertable);
     
    var trafficallPanel = new Ext.grid.GridPanel({
        store: trafficallstore,
        columnWidth:0.4,   height: 500,
        columns:[new Ext.grid.RowNumberer(),
                 {id:'names',header: "IP/用户ID", width: 280, sortable: true,  dataIndex: 'userName'},
                 {header: "流量(MB)", width: 100, sortable: true,  dataIndex: 'userValue'}
            ]
    }) 
    var trafficinPanel = new Ext.grid.GridPanel({
        store: trafficinstore,
        columnWidth:0.4,  height: 500,
        columns:[new Ext.grid.RowNumberer(),
                 {id:'names',header: "IP/用户ID", width: 280, sortable: true,  dataIndex: 'userName'},
                 {header: "流量(MB)", width: 100, sortable: true,  dataIndex: 'userValue'}
            ]
    }) 
    var trafficoutPanel = new Ext.grid.GridPanel({
        store: trafficoutstore,
        columnWidth:0.4,  height: 500,
        columns:[new Ext.grid.RowNumberer(),
                 {id:'names',header: "IP/用户ID", width: 280, sortable: true,  dataIndex: 'userName'},
                 {header: "流量(MB)", width: 100, sortable: true,  dataIndex: 'userValue'}
            ]
    }) 
    var otherPanel = new Ext.grid.GridPanel({
        store: otherstore,
        columnWidth:0.4,   height: 500,
        columns:[new Ext.grid.RowNumberer(),
                 {id:'names',header: "IP/用户ID", width: 280, sortable: true,  dataIndex: 'userName'},
                 {header: "流量(MB)", width: 100, sortable: true,  dataIndex: 'userValue'}
            ],
        autoExpandColumn: 'names'
    }) 
    
    var chartTrafficall= new Ext.Panel({
	  columnWidth:0.6,
      //html:'<img src="file/analysis/downLoadFile/pic/port80.jpg"></img>'
      html:'<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left"><tr><td valign="top" class="text" align="left"><div id="chart80div" align="left"></div></td></tr></table>'
	})
	var chartTrafficin= new Ext.Panel({
	  columnWidth:0.6,
      //html:'<img src="file/analysis/downLoadFile/pic/port18600.jpg"></img>'
      html:'<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left"><tr><td valign="top" class="text" align="left"><div id="chart18600div" align="left"></div></td></tr></table>'
	})
    var chartTrafficout= new Ext.Panel({
	  columnWidth:0.6,
      //html:'<img src="file/analysis/downLoadFile/pic/port16703.jpg"></img>'
      html:'<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left"><tr><td valign="top" class="text" align="left"><div id="chart16703div" align="left"></div></td></tr></table>'
	})
    var chartOther= new Ext.Panel({
	  columnWidth:0.6,
      //html:'<img src="file/analysis/downLoadFile/pic/portother.jpg"></img>'
      html:'<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left"><tr><td valign="top" class="text" align="left"><div id="chartotherdiv" align="left"></div></td></tr></table>'
	})
	var cen80Html = '';
	var cen18600Html = '';
	var cen16703Html = '';
	var cenotherHtml = '';
	var topHtml = '';
	var endHtml = '';
	
	setTimeout(function(){
		var chart80Flex = new FusionCharts('flex/Line.swf', "ChartRId", "100%", "480");
		var chart18600Flex = new FusionCharts('flex/Line.swf', "ChartRId", "100%", "480");
		var chart16703Flex = new FusionCharts('flex/Line.swf', "ChartRId", "100%", "480");
		var chartotherFlex = new FusionCharts('flex/Line.swf', "ChartRId", "100%", "480");
		getChHtml();
		chart80Flex.setDataXML(encodeURI(topHtml+cen80Html+endHtml));		   
		chart80Flex.render('chart80div');
		chart18600Flex.setDataXML(encodeURI(topHtml+cen18600Html+endHtml));		   
		chart18600Flex.render('chart18600div');
		chart16703Flex.setDataXML(encodeURI(topHtml+cen16703Html+endHtml));		   
		chart16703Flex.render('chart16703div');	
		chartotherFlex.setDataXML(encodeURI(topHtml+cenotherHtml+endHtml));		   
		chartotherFlex.render('chartotherdiv');
	
	}, 1000);
   function getChHtml(){
   		topHtml = '<chart yAxisName="" showValues="0" decimals="0" numberSuffix="MB" lineColor="FCB541" Decimals="2" >';
		endHtml = '</chart>';
		var count80=0;
		var count18600=0;
		var count16703=0;
		var countother=0;
		for (i=0;i<temp.length;i++){
	       typeName[i] = temp[i].typeName;      
	       userName[i] = temp[i].userName;
	       userValue[i] = temp[i].userValue;
	       if (typeName[i]=="port80"){
	       	  count80 = count80+1;
	          cen80Html += '<set label="'+count80+'" toolText="'+userName[i]+'的流量为：'+userValue[i]+'MB" value="'+userValue[i]+'" />';
	       }else if(typeName[i] == "port18600"){
	       	  count18600 = count18600+1;
	       	  cen18600Html += '<set label="'+count18600+'"toolText="'+userName[i]+'的流量为：'+userValue[i]+'MB" value="'+userValue[i]+'" />';
	       }else if (typeName[i] == "port16703"){
	       	  count16703 = count16703+1;
	          cen16703Html += '<set label="'+count16703+'"toolText="'+userName[i]+'的流量为：'+userValue[i]+'MB" value="'+userValue[i]+'" />';
	       }else if (typeName[i] == "portother"){
	       	  countother = countother+1;
	          cenotherHtml += '<set label="'+countother+'"toolText="'+userName[i]+'的流量为：'+userValue[i]+'MB" value="'+userValue[i]+'" />';
	       }  
	    }
	}
	 
	var Panel = new Ext.Panel({
	   width : 1000, 
	   height: 500,
	   items : [{
	   xtype:'panel',
	   layout:"column",
	   title:'www端口TopN排序',
	   items:[trafficallPanel,chartTrafficall]
	   }]
   })

   var Panel1 = new Ext.Panel({
	   width : 1000, 
	   height: 500,
	   items : [{
	   xtype:'panel',
	   layout:"column",
	   title:'BUPT-BT端口TopN排序',
	   items:[trafficinPanel,chartTrafficin]
	   }]
   })
    
   var Panel2 = new Ext.Panel({
	   width : 1000, 
	   height: 500,
	   items : [{
	   xtype:'panel',
	   layout:"column",
	   title:'NEU-BT端口TopN排序',
	   items:[trafficoutPanel,chartTrafficout]
	   }]
   })
   
   var Panel3 = new Ext.Panel({
	   width : 1000, 
	   height: 500,
	   items : [{
	   xtype:'panel',
	   layout:"column",
	   title:'other端口TopN排序',
	   items:[otherPanel,chartOther]
	   }]
   })
   
  var Panelall = new Ext.Panel({
	   xtype:'panel',
	   items:[ Panel,Panel1,Panel2,Panel3] })  
	Panelall.render('showGrid');
   })
})
</script> 

</head>
<body bgcolor="#FFFFFF" link="#000080" alink="#0000FF" leftmargin="20" topmargin="10">

	<font face="Arial" size="+1" color=#000000 text-align="center"><CENTER>TopN用户流量(1分钟)</CENTER></font>
	<p>
	<hr style="border: 1px dashed #ccc; width: 100%; height: 5px;" />
	<!--<hr width="100%" color="#000000" size=2>-->
	<a href="userTraffic.do">流量</a> | <a href="userPacket.do">包数</a> | <a href="userProtocol.do">协议</a>| <b><font color=#0000FF >端口</font></b>
	<p>
	<br>
	<div id="outer">
		<div id="bodyDiv">
			<div id="infoDiv">
				<div id='showGrid'></div>
			</div>
		</div>
	</div>
</body>
</html>
