(function () {
    require.config({
        paths: {
            echarts: "echarts",
        },
    });

    require(
        [
            "echarts",
            "echarts/chart/main",
            "echarts/chart/map",
        ],
        function (echarts, BMapExtension) {
            $('#main').css({
                height: $('body').height(),
                width: $('body').width()
            });

            // 初始化地图
            var BMapExt = new BMapExtension($('#main')[0], BMap, echarts, {
                enableMapClick: false
            });
            var map = BMapExt.getMap();
            var container = BMapExt.getEchartsContainer();

            var startPoint = {
                x: 113.915371776721,//初始地图中心位置
                y: 22.520829991238944
            };

            var point = new BMap.Point(startPoint.x, startPoint.y);
            map.centerAndZoom(point, 17);
            map.enableScrollWheelZoom(true);
            // 地图自定义样式
            map.setMapStyle({
                styleJson: [
                    {
                        'featureType': 'land',     //调整土地颜色
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#081734'
                        }
                    },
                    {
                        'featureType': 'building',   //调整建筑物颜色
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#04406F'
                        }
                    },
                    {
                        'featureType': 'building',   //调整建筑物标签是否可视
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'highway',     //调整高速道路颜色
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#015B99'
                        }
                    },
                    {
                        'featureType': 'highway',    //调整高速名字是否可视
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'arterial',   //调整一些干道颜色
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#003051'
                        }
                    },
                    {
                        'featureType': 'arterial',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'green',
                        'elementType': 'geometry',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'water',
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#044161'
                        }
                    },
                    {
                        'featureType': 'subway',    //调整地铁颜色
                        'elementType': 'geometry.stroke',
                        'stylers': {
                            'color': '#003051'
                        }
                    },
                    {
                        'featureType': 'subway',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'railway',
                        'elementType': 'geometry',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'railway',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'all',     //调整所有的标签的边缘颜色
                        'elementType': 'labels.text.stroke',
                        'stylers': {
                            'color': '#313131'
                        }
                    },
                    {
                        'featureType': 'all',     //调整所有标签的填充颜色
                        'elementType': 'labels.text.fill',
                        'stylers': {
                            'color': '#FFFFFF'
                        }
                    },
                    {
                        'featureType': 'manmade',
                        'elementType': 'geometry',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'manmade',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'local',
                        'elementType': 'geometry',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'local',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    },
                    {
                        'featureType': 'subway',
                        'elementType': 'geometry',
                        'stylers': {
                            'lightness': -65
                        }
                    },
                    {
                        'featureType': 'railway',
                        'elementType': 'all',
                        'stylers': {
                            'lightness': -40
                        }
                    },
                    {
                        'featureType': 'boundary',
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#8b8787',
                            'weight': '1',
                            'lightness': -29
                        }
                    }
                ]
            });

            option = {

                color: ['gold', 'aqua', 'lime'],
                title: {
                    text: '',
                    subtext: '',
                    x: 'center',
                    textStyle: {
                        color: '#fff',
                        fontSize: 20,
                        fontWeight: 'bold',
                    }
                },
                tooltip: {
                    show: true,
                    trigger: 'item',
                    hideDelay: 4000,
                    formatter: function (d) {
                        var jw = '经度：' + d.value[0] + '<br/>'
                        jw += '纬度：' + d.value[1]
                        return jw
                    }
                },
                color: ['gold', 'red'],
                legend: {
                    data: ['可视化轨迹', '其他轨迹'],
                    x: 'left',
                    orient: 'vertical',
                    padding: [30, 15, 15, 30],
                    textStyle: {
                        fontSize: 17,
                        color: 'rgb(204,204,204)',
                    },
                    selected: {
                        '可视化轨迹': true,
                        '其他轨迹': false,
                    },
                    selectedMode: 'single',
                },
                /*
                toolbox: {
                    show : true,
                    orient : 'vertical',
                    x: 'right',
                    y: 'center',
                    feature : {
                       mark : {show: true},
                       dataView : {show: true, readOnly: false},
                       restore : {show: true},
                       saveAsImage : {show: true}
                    }
                },*/
                series: [
                    {
                        name: '可视化轨迹',
                        type: 'map',
                        mapType: 'none',
                        data: [],

                        markLine: {
                            Symbol: ['none', 'arrow'],
                            symbolSize: ['0', '0.1'],
                            smooth: true,
                            smooth: 0,
                            effect: {
                                show: true,
                                scaleSize: 1,
                                period: 30,
                                color: '#fff',
                                shadowBlur: 10
                            },
                            itemStyle: {
                                color: 'red',
                                normal: {
                                    color: function (param) {
                                        return (param.data[0].value.colorValue);
                                    },
                                    borderWidth: 3,
                                    lineStyle: {
                                        type: 'solid',
                                        width: 3,
                                        shadowBlur: 10
                                    },
                                    label: {show: false, value: 'park'}
                                }
                            },

                            data: [
                                [{name: 'p0'},
                                    {name: 'p1', value: {colorValue: 'gold'}}], [{name: 'p1'},
                                    {name: 'p2', value: {colorValue: 'gold'}}], [{name: 'p2'},
                                    {name: 'p3', value: {colorValue: 'gold'}}], [{name: 'p3'},
                                    {name: 'p4', value: {colorValue: 'gold'}}], [{name: 'p4'},
                                    {name: 'p5', value: {colorValue: 'gold'}}], [{name: 'p5'},
                                    {name: 'p6', value: {colorValue: 'gold'}}], [{name: 'p6'},
                                    {name: 'p7', value: {colorValue: 'gold'}}], [{name: 'p7'},
                                    {name: 'p8', value: {colorValue: 'gold'}}], [{name: 'p8'},
                                    {name: 'p9', value: {colorValue: 'gold'}}], [{name: 'p9'},
                                    {name: 'p10', value: {colorValue: 'gold'}}], [{name: 'p10'},
                                    {name: 'p11', value: {colorValue: 'gold'}}], [{name: 'p11'},
                                    {name: 'p12', value: {colorValue: 'gold'}}], [{name: 'p12'},
                                    {name: 'p13', value: {colorValue: 'gold'}}], [{name: 'p13'},
                                    {name: 'p14', value: {colorValue: 'gold'}}], [{name: 'p14'},
                                    {name: 'p15', value: {colorValue: 'gold'}}], [{name: 'p15'},
                                    {name: 'p16', value: {colorValue: 'gold'}}], [{name: 'p16'},
                                    {name: 'p17', value: {colorValue: 'gold'}}], [{name: 'p17'},
                                    {name: 'p18', value: {colorValue: 'gold'}}], [{name: 'p18'},
                                    {name: 'p19', value: {colorValue: 'gold'}}], [{name: 'p19'},
                                    {name: 'p20', value: {colorValue: 'gold'}}], [{name: 'p20'},
                                    {name: 'p21', value: {colorValue: 'gold'}}], [{name: 'p21'},
                                    {name: 'p22', value: {colorValue: 'gold'}}], [{name: 'p22'},
                                    {name: 'p23', value: {colorValue: 'gold'}}], [{name: 'p23'},
                                    {name: 'p24', value: {colorValue: 'gold'}}], [{name: 'p24'},
                                    {name: 'p25', value: {colorValue: 'gold'}}], [{name: 'p25'},
                                    {name: 'p26', value: {colorValue: 'gold'}}], [{name: 'p26'},
                                    {name: 'p27', value: {colorValue: 'gold'}}], [{name: 'p27'},
                                    {name: 'p28', value: {colorValue: 'gold'}}], [{name: 'p28'},
                                    {name: 'p29', value: {colorValue: 'gold'}}], [{name: 'p29'},
                                    {name: 'p30', value: {colorValue: 'gold'}}], [{name: 'p30'},
                                    {name: 'p31', value: {colorValue: 'gold'}}], [{name: 'p31'},
                                    {name: 'p32', value: {colorValue: 'gold'}}], [{name: 'p32'},
                                    {name: 'p33', value: {colorValue: 'gold'}}], [{name: 'p33'},
                                    {name: 'p34', value: {colorValue: 'gold'}}], [{name: 'p34'},
                                    {name: 'p35', value: {colorValue: 'gold'}}], [{name: 'p35'},
                                    {name: 'p36', value: {colorValue: 'gold'}}], [{name: 'p36'},
                                    {name: 'p37', value: {colorValue: 'gold'}}], [{name: 'p37'},
                                    {name: 'p38', value: {colorValue: 'gold'}}], [{name: 'p38'},
                                    {name: 'p39', value: {colorValue: 'gold'}}], [{name: 'p39'},
                                    {name: 'p40', value: {colorValue: 'gold'}}], [{name: 'p40'},
                                    {name: 'p41', value: {colorValue: 'gold'}}], [{name: 'p41'},
                                    {name: 'p42', value: {colorValue: 'gold'}}], [{name: 'p42'},
                                    {name: 'p43', value: {colorValue: 'gold'}}], [{name: 'p43'},
                                    {name: 'p44', value: {colorValue: 'gold'}}], [{name: 'p44'},
                                    {name: 'p45', value: {colorValue: 'gold'}}], [{name: 'p45'},
                                    {name: 'p46', value: {colorValue: 'gold'}}], [{name: 'p46'},
                                    {name: 'p47', value: {colorValue: 'gold'}}], [{name: 'p47'},
                                    {name: 'p48', value: {colorValue: 'gold'}}], [{name: 'p48'},
                                    {name: 'p49', value: {colorValue: 'gold'}}], [{name: 'p49'},
                                    {name: 'p50', value: {colorValue: 'gold'}}], [{name: 'p50'},
                                    {name: 'p51', value: {colorValue: 'gold'}}], [{name: 'p51'},
                                    {name: 'p52', value: {colorValue: 'gold'}}], [{name: 'p52'},
                                    {name: 'p53', value: {colorValue: 'gold'}}], [{name: 'p53'},
                                    {name: 'p54', value: {colorValue: 'gold'}}], [{name: 'p54'},
                                    {name: 'p55', value: {colorValue: 'gold'}}], [{name: 'p55'},
                                    {name: 'p56', value: {colorValue: 'gold'}}], [{name: 'p56'},
                                    {name: 'p57', value: {colorValue: 'gold'}}], [{name: 'p57'},
                                    {name: 'p58', value: {colorValue: 'gold'}}], [{name: 'p58'},
                                    {name: 'p59', value: {colorValue: 'gold'}}], [{name: 'p59'},
                                    {name: 'p60', value: {colorValue: 'gold'}}], [{name: 'p60'},
                                    {name: 'p61', value: {colorValue: 'gold'}}], [{name: 'p61'},
                                    {name: 'p62', value: {colorValue: 'gold'}}], [{name: 'p62'},
                                    {name: 'p63', value: {colorValue: 'gold'}}], [{name: 'p63'},
                                    {name: 'p64', value: {colorValue: 'gold'}}], [{name: 'p64'},
                                    {name: 'p65', value: {colorValue: 'gold'}}], [{name: 'p65'},
                                    {name: 'p66', value: {colorValue: 'gold'}}], [{name: 'p66'},
                                    {name: 'p67', value: {colorValue: 'gold'}}], [{name: 'p67'},
                                    {name: 'p68', value: {colorValue: 'gold'}}], [{name: 'p68'},
                                    {name: 'p69', value: {colorValue: 'gold'}}], [{name: 'p69'},
                                    {name: 'p70', value: {colorValue: 'gold'}}], [{name: 'p70'},
                                    {name: 'p71', value: {colorValue: 'gold'}}], [{name: 'p71'},
                                    {name: 'p72', value: {colorValue: 'gold'}}], [{name: 'p72'},
                                    {name: 'p73', value: {colorValue: 'gold'}}], [{name: 'p73'},
                                    {name: 'p74', value: {colorValue: 'gold'}}], [{name: 'p74'},
                                    {name: 'p75', value: {colorValue: 'gold'}}], [{name: 'p75'},
                                    {name: 'p76', value: {colorValue: 'gold'}}], [{name: 'p76'},
                                    {name: 'p77', value: {colorValue: 'gold'}}], [{name: 'p77'},
                                    {name: 'p78', value: {colorValue: 'gold'}}], [{name: 'p78'},
                                    {name: 'p79', value: {colorValue: 'gold'}}], [{name: 'p79'},
                                    {name: 'p80', value: {colorValue: 'gold'}}], [{name: 'p80'},
                                    {name: 'p81', value: {colorValue: 'gold'}}], [{name: 'p81'},
                                    {name: 'p82', value: {colorValue: 'gold'}}], [{name: 'p82'},
                                    {name: 'p83', value: {colorValue: 'gold'}}], [{name: 'p83'},
                                    {name: 'p84', value: {colorValue: 'gold'}}], [{name: 'p84'},
                                    {name: 'p85', value: {colorValue: 'gold'}}], [{name: 'p85'},
                                    {name: 'p86', value: {colorValue: 'gold'}}], [{name: 'p86'},
                                    {name: 'p87', value: {colorValue: 'gold'}}], [{name: 'p87'},
                                    {name: 'p88', value: {colorValue: 'gold'}}], [{name: 'p88'},
                                    {name: 'p89', value: {colorValue: 'gold'}}], [{name: 'p89'},
                                    {name: 'p90', value: {colorValue: 'gold'}}], [{name: 'p90'},
                                    {name: 'p91', value: {colorValue: 'gold'}}], [{name: 'p91'},
                                    {name: 'p92', value: {colorValue: 'gold'}}], [{name: 'p92'},
                                    {name: 'p93', value: {colorValue: 'gold'}}], [{name: 'p93'},
                                    {name: 'p94', value: {colorValue: 'gold'}}], [{name: 'p94'},
                                    {name: 'p95', value: {colorValue: 'gold'}}], [{name: 'p95'},
                                    {name: 'p96', value: {colorValue: 'gold'}}], [{name: 'p96'},
                                    {name: 'p97', value: {colorValue: 'gold'}}]
                            ]
                        },
                        markPoint: {
                            symbol: 'image://./image/location.svg',
                            symbolSize: function (v) {
                                return v / 5
                            },
                            effect: {
                                show: true,
                                type: 'bounce',
                                period: 3,
                            },
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: false,
                                    },
                                },
                                emphasis: {
                                    label: {
                                        show: false,
                                    },
                                },
                            },
                            data: [
                                {
                                    name: 'p0', value: 50,
                                    tooltip: {
                                        formatter: '起点<br/>经度:113.9105185<br/>纬度:22.52365441'
                                    },
                                },
                                {
                                    name: 'p97', value: 100,
                                    tooltip: {
                                        formatter: '终点<br/>经度:113.9251142<br/>纬度:22.53439972'
                                    },
                                }
                            ],
                        },
                        geoCoord: {
                            'p0':[113.9105148,22.52365494],
                            'p1':[113.9100189,22.52409744],
                            'p2':[113.9098434,22.52425385],
                            'p3':[113.9095154,22.52453613],
                            'p4':[113.9094925,22.52455711],
                            'p5':[113.9093628,22.52467537],
                            'p6':[113.9090805,22.52492905],
                            'p7':[113.9083023,22.52561188],
                            'p8':[113.9075394,22.52627182],
                            'p9':[113.9068680,22.52687454],
                            'p10':[113.9065094,22.52638435],
                            'p11':[113.9057999,22.52569008],
                            'p12':[113.9052353,22.52513885],
                            'p13':[113.9046783,22.52458382],
                            'p14':[113.9026642,22.52391815],
                            'p15':[113.9020767,22.52442169],
                            'p16':[113.9014435,22.52500916],
                            'p17':[113.9005661,22.52578354],
                            'p18':[113.9002151,22.52608681],
                            'p19':[113.8995056,22.52547264],
                            'p20':[113.8979797,22.52411842],
                            'p21':[113.8976135,22.52379417],
                            'p22':[113.8976059,22.52378654],
                            'p23':[113.8979721,22.52389717],
                            'p24':[113.8989334,22.52473068],
                            'p25':[113.8996429,22.52534485],
                            'p26':[113.9003601,22.52577400],
                            'p27':[113.9011002,22.52523041],
                            'p28':[113.9019165,22.52449799],
                            'p29':[113.9024963,22.52398491],
                            'p30':[113.9030762,22.52348518],
                            'p31':[113.9030914,22.52346611],
                            'p32':[113.9031372,22.52342796],
                            'p33':[113.9034195,22.52323532],
                            'p34':[113.9040680,22.52389717],
                            'p35':[113.9048004,22.52461624],
                            'p36':[113.9053497,22.52515602],
                            'p37':[113.9061050,22.52589989],
                            'p38':[113.9067764,22.52641678],
                            'p39':[113.9074020,22.52625084],
                            'p40':[113.9096375,22.52425957],
                            'p41':[113.9102097,22.52374649],
                            'p42':[113.9104080,22.52357292],
                            'p43':[113.9109116,22.52311897],
                            'p44':[113.9111633,22.52289963],
                            'p45':[113.9112091,22.52286339],
                            'p46':[113.9117279,22.52241325],
                            'p47':[113.9121017,22.52209473],
                            'p48':[113.9122543,22.52196884],
                            'p49':[113.9125595,22.52171135],
                            'p50':[113.9130402,22.52130127],
                            'p51':[113.9131470,22.52120590],
                            'p52':[113.9133377,22.52103043],
                            'p53':[113.9141083,22.52033424],
                            'p54':[113.9160614,22.51884651],
                            'p55':[113.9162827,22.51873970],
                            'p56':[113.9168015,22.51848030],
                            'p57':[113.9169998,22.51838112],
                            'p58':[113.9171600,22.51830101],
                            'p59':[113.9177628,22.51800919],
                            'p60':[113.9195557,22.51774979],
                            'p61':[113.9198608,22.51778412],
                            'p62':[113.9201202,22.51892662],
                            'p63':[113.9201431,22.51903343],
                            'p64':[113.9202194,22.51931572],
                            'p65':[113.9202423,22.51940727],
                            'p66':[113.9202728,22.51951408],
                            'p67':[113.9205322,22.52029037],
                            'p68':[113.9224396,22.52168846],
                            'p69':[113.9226990,22.52180290],
                            'p70':[113.9227524,22.52182961],
                            'p71':[113.9229126,22.52190971],
                            'p72':[113.9235535,22.52233124],
                            'p73':[113.9240112,22.52283287],
                            'p74':[113.9244080,22.52347183],
                            'p75':[113.9244232,22.52349663],
                            'p76':[113.9250793,22.52465630],
                            'p77':[113.9253387,22.52510452],
                            'p78':[113.9256897,22.52593994],
                            'p79':[113.9257584,22.52616501],
                            'p80':[113.9257889,22.52632904],
                            'p81':[113.9258957,22.52730560],
                            'p82':[113.9259720,22.52853394],
                            'p83':[113.9259796,22.52865982],
                            'p84':[113.9260788,22.52917099],
                            'p85':[113.9261017,22.52924919],
                            'p86':[113.9261703,22.52994728],
                            'p87':[113.9261551,22.52997780],
                            'p88':[113.9260025,22.53045464],
                            'p89':[113.9259567,22.53089333],
                            'p90':[113.9259262,22.53120995],
                            'p91':[113.9259262,22.53123665],
                            'p92':[113.9259033,22.53146362],
                            'p93':[113.9254608,22.53291702],
                            'p94':[113.9253693,22.53317642],
                            'p95':[113.9253540,22.53322220],
                            'p96':[113.9251328,22.53382492],
                            'p97':[113.9251175,22.53439903]
                        }
                    },


                    {
                        name: '其他轨迹',
                        type: 'map',
                        mapType: 'none',
                        data: [],

                        markLine: {
                            Symbol: ['none', 'arrow'],
                            symbolSize: ['0', '0.1'],
                            smooth: true,
                            smooth: 20,
                            effect: {
                                show: true,
                                scaleSize: 1,
                                period: 30,
                                color: '#fff',
                                shadowBlur: 10
                            },
                            itemStyle: {
                                color: 'red',
                                normal: {
                                    color: function (param) {
                                        return (param.data[0].value.colorValue);
                                    },
                                    borderWidth: 3,
                                    lineStyle: {
                                        type: 'solid',
                                        width: 3,
                                        shadowBlur: 10
                                    },
                                    label: {show: false, value: '天河城'}
                                }
                            },

                            data: [
                                [{name: 's1'}, {name: 's2', value: {colorValue: 'gold'}}],
                                [{name: 's2'}, {name: 's3', value: {colorValue: 'gold'}}],
                                [{name: 's3'}, {name: 's4', value: {colorValue: 'gold'}}],
                                [{name: 's4'}, {name: 's10', value: {colorValue: 'gold'}}],
                                [{name: 's10'}, {name: 's11', value: {colorValue: 'gold'}}],
                                [{name: 's5'}, {name: 's6', value: {colorValue: 'gold'}}],
                                [{name: 's6'}, {name: 's7', value: {colorValue: 'gold'}}],
                                [{name: 's7'}, {name: 's8', value: {colorValue: 'gold'}}],
                                [{name: 's8'}, {name: 's9', value: {colorValue: 'gold'}}],
                                [{name: 's9'}, {name: 's10', value: {colorValue: 'gold'}}],
                                [{name: 's10'}, {name: 's11', value: {colorValue: 'gold'}}],
                            ]
                        },
                        markPoint: {
                            symbol: 'emptyCircle',
                            symbolSize: function (v) {
                                return v / 5
                            },
                            effect: {
                                show: true,
                                type: 'scale',
                                period: 10,
                                color: 'gold',
                            },
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: false,
                                    },
                                },
                                emphasis: {
                                    label: {
                                        show: false,
                                    },
                                },
                            },
                            data: [
                                {
                                    name: 's1', value: 50,
                                    tooltip: {
                                        formatter: '小乙<br/>时间:8:30am<br/>出发地:东兴小区'
                                    },
                                },
                                {
                                    name: 's5', value: 50,
                                    tooltip: {
                                        formatter: '小丙<br/>时间:8:10am<br/>出发地:冼村'
                                    },
                                },
                                {
                                    name: 's10', value: 50,
                                    //tooltip:{
                                    //   formatter:'目的地天河城<br/>经度:113.328755<br/>纬度:23.137588'
                                    //},
                                },
                                {
                                    name: 's11', value: 100,
                                    tooltip: {
                                        formatter: '时间:10:00am<br/>目的地:天河城购物广场<br/>小乙与小丙成功会合<br/>开始一天的逛街之旅'
                                    },
                                },
                            ],
                        },
                        geoCoord: {
                            's1': [113.319283, 23.129146],
                            's2': [113.321817, 23.129877],
                            's3': [113.32776, 23.129612],
                            's4': [113.328069, 23.136798],
                            's5': [113.336953, 23.131398],
                            's6': [113.336163, 23.132711],
                            's7': [113.331384, 23.132976],
                            's8': [113.330916, 23.132212],
                            's9': [113.330701, 23.13681],
                            's10': [113.329749, 23.137463],
                            's11': [113.328976, 23.137949],
                        }
                    },

                ]
            };


            var myChart = BMapExt.initECharts(container);
            window.onresize = myChart.onresize;
            BMapExt.setOption(option);
        }
    );
})();

