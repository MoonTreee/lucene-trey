<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>   <!-- 引入 echarts.js -->
	<script src="content/js/echarts.min.js"></script></head>
<title>indexData</title>
<body>
<a href="lucene_index.action">更新索引</a>
<form action="lucene_search.action" method="post" target="top">
	<div class="loginBox" align="center>
		<ul class="search">
			<li class="l_tit">检索关键词</li>
			<li class="mb_10"><input type="text" name="keyword" class="login_input user_icon"></li>
			<li><input type="submit" value="检索" class="login_btn"></li>
		</ul>
	</div>
</form>

<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    var radarStyle = document.createElement("style");
    var str = `
    #radar:after {
	  content: ' ';
	  display: block;
	  background-image: linear-gradient(44deg, rgba(0, 255, 51, 0) 50%, #00ff33 100%);
	  width: 180px;
	  height: 180px;
	  position: absolute;
	  top: 14%;
	  left: 14%;
	  animation: radar-beam 4s infinite;
	  animation-timing-function: linear;
	  transform-origin: bottom right;
	  border-radius: 100% 0 0 0;
	}
	@keyframes radar-beam {
	  0% {
	    transform: rotate(0deg);
	  }
	  100% {
	    transform: rotate(360deg);
	  }
	}
`;
    if(radarStyle.styleSheet){         //ie下
        radarStyle.styleSheet.cssText = str;
    } else {
        radarStyle.innerHTML = str;
    };
    document.getElementsByTagName("head")[0].appendChild(radarStyle);

    var radarDiv = document.createElement('div');
    radarDiv.id = 'radar';
    radarDiv.style.position = 'relative';
    radarDiv.style.width = '500px';
    radarDiv.style.height = '500px';
    radarDiv.style.margin = '0 auto';
    var body = document.querySelector('body');
    body.appendChild(radarDiv);

    var mockData = [{
        text: '指标一',
        value: 2
    }, {
        text: '指标二',
        value: 3.2
    }, {
        text: '指标三',
        value: 5
    }, {
        text: '指标四',
        value: 4.1
    }, {
        text: '指标五',
        value: 2.8
    }];

    var container = document.getElementById('radar');
    var radar = echarts.init(container);

    initRadar(mockData);

    function initRadar(mockData){
        var minAngle = 10;	// 最小角度
        var indicator = [];
        var data = [];
        var idx = 0;
        var randomArr = getRandom(360 / minAngle, mockData.length);
        for(var i=0;i<360 / minAngle;i++){
            if(randomArr.indexOf(i) > -1){
                indicator.push({
                    text: mockData[idx].text
                });
                data.push(mockData[idx].value);
                idx++;
            }else{
                indicator.push({
                    text: i * 10 + '`'
                });
                data.push('-');
            }
        };
        var option = {
            backgroundColor: '#333',
            radar: [
                {
                    indicator: indicator,
                    center: ['50%', '50%'],
                    radius: 180,
                    startAngle: 90,
                    splitNumber: 4,
                    shape: 'circle',
                    name: {
                        show: false,
                        // formatter:'【{value}】',
                        // textStyle: {
                        //     color:'rgba(0, 255, 51, 1)'
                        // }
                    },
                    splitArea: {
                        areaStyle: {
                            color: 'rgba(255, 255, 255, 0)'
                        }
                    },
                    axisLine: {
                        lineStyle: {
                            color: 'rgba(0, 255, 51, 0)'
                        }
                    },
                    splitLine: {
                        lineStyle: {
                            color: 'rgba(0, 255, 51, 1)'
                        }
                    }
                }
            ],
            series: [
                {
                    name: '雷达图',
                    type: 'radar',
                    symbol: 'circle',
                    symbolSize: 24,
                    silent: true,
                    animationEasing: 'quarticOut',
                    animationEasingUpdate: 'quarticOut',
                    animationDuration: 2000,
                    animationDurationUpdate: 2000,
                    label: {
                        normal: {
                            show: true,
                            textStyle: {
                                color: 'rgba(0, 255, 51, 1)'
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            opacity: 0
                        },
                        emphasis: {
                            color: {
                                type: 'radial',
                                x: 0.5,
                                y: 0.5,
                                r: 0.3,
                                colorStops: [{
                                    offset: 0,
                                    color: 'rgba(0, 255, 51, 1)'
                                }, {
                                    offset: 1,
                                    color: 'rgba(255, 255, 255, .1)'
                                }]
                            },
                            borderWidth: 0,
                            opacity: 1
                        }
                    },
                    data: [
                        {
                            value: data,
                            label: {
                                normal: {
                                    textStyle: {
                                        color: 'rgba(0, 255, 51, 1)'
                                    }
                                }
                            },
                            lineStyle: {
                                normal: {
                                    opacity: 0
                                }
                            }
                        }
                    ]
                }
            ]
        };

        radar.setOption(option);

        function getRandom(range, count){
            var randomArr = [];
            for(var i = 0;i < count;i++){
                var r = Math.round(Math.random() * (range - 1));
                randomArr.push(r);
            }
            return randomArr;
        }
    }

    // 点动画
    (function(){
        var highlight = false;
        var type;
        setInterval(function(){
            initRadar(mockData);
            type = highlight ? 'downplay' : 'highlight';
            radar.dispatchAction({
                type: type,
                seriesIndex: 0
            });
            highlight = !highlight;
        }, 2000);
    })();

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
