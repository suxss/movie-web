Object.prototype.parseHTMLTemplate = function (functionObject) {
    return function (scope) {
        return functionObject.toString().match(/\/\*([\s\S]*?)\*\//)[1].replace(/\$\{\w.+\}/g, function (variable) {
            var value = scope;
            variable = variable.replace('${', '').replace('}', '');
            variable.split('.').forEach(function (section) {
                value = value[section];
            });
            return value;
        });
    }
};

function max(a, b) {
    return a > b ? a : b;
}

var template1 = parseHTMLTemplate(function () {
    /*
    <div class="row card-deck">
        <div class="col">
            <div class="card mb-12" id="info-card">
                <div class="row no-gutters">
                    <div class="col-md-3">
                        <img class="card-img"
                             src="${cover}"
                             alt="Card image cap" referrerPolicy="no-referrer">
                    </div>
                    <div class="col-mb-8">
                        <div class="card-body">
                            <h4 class="card-title">${title}</h4>
                            <div class="content">
                                <div class="row" style="width: 400px">
                                    <div class="col-6">
                                        <div class="el-rate">
                                               <span class="el-rate__item" style="cursor: auto;">
                                                    <i class="el-rate__icon el-icon-star-on"
                                                       style="color: rgb(239, 242, 247);">
                                                        <i class="el-rate__decimal el-icon-star-on"
                                                           style="color: rgb(247, 186, 42); width: ${yellow1}%;">
                                                        </i>
                                                    </i>
                                                </span>
                                            <span class="el-rate__item" style="cursor: auto;">
                                                    <i class="el-rate__icon el-icon-star-on"
                                                       style="color: rgb(239, 242, 247);">
                                                        <i class="el-rate__decimal el-icon-star-on"
                                                           style="color: rgb(247, 186, 42); width: ${yellow2}%;">
                                                        </i>
                                                    </i>
                                                </span>
                                            <span class="el-rate__item" style="cursor: auto;">
                                                    <i class="el-rate__icon el-icon-star-on"
                                                       style="color: rgb(239, 242, 247);">
                                                        <i class="el-rate__decimal el-icon-star-on"
                                                           style="color: rgb(247, 186, 42); width: ${yellow3}%;">
                                                        </i>
                                                    </i>
                                                </span>
                                            <span class="el-rate__item" style="cursor: auto;">
                                                    <i class="el-rate__icon el-icon-star-on"
                                                       style="color: rgb(239, 242, 247);">
                                                        <i class="el-rate__decimal el-icon-star-on"
                                                           style="color: rgb(247, 186, 42); width: ${yellow4}%;">
                                                        </i>
                                                    </i>
                                                </span>
                                            <span class="el-rate__item" style="cursor: auto;">
                                                    <i class="el-rate__icon el-icon-star-on"
                                                       style="color: rgb(239, 242, 247);">
                                                        <i class="el-rate__decimal el-icon-star-on"
                                                           style="color: rgb(247, 186, 42); width: ${yellow5}%;">
                                                        </i>
                                                    </i>
                                                </span>
                                            <span class="el-rate__text" style="color: rgb(255, 153, 0);">
                                                      ${rate}
                                                  </span>
                                        </div>
                                    </div>
                                    <div class="col" style="top: -4px">
                                        <span class="badge badge-primary">剧情</span>
                                    </div>
                                </div>
                                <div id="people">
                                    <div id="directors">
                                        <p>导演：${director}</p>
                                    </div>
                                    <div id="actors">
                                        <p>主演：${actors}</p>
                                    </div>
                                    <div id="imdb">
                                        <p>IMDb：${IMDb}</p>
                                    </div>

                                    <div id="echart" style="width: 600px;height:170px;"></div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row card-deck">
        <div class="col">
            <div class="card mb-12" id="description">
                <div class="row no-gutters">
                    <div class="col">
                        <div class="card-body">
                            <h4 class="card-title">剧情简介</h4>
                            <div class="content">
                                <div id="desc-content">
                                    ${content}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-5 card-deck">

        <div class="card" onClick="window.open('${r1-href}')">
            <img class="card-img-top" src="${r1-cover}" alt="Card image cap">
                <div class="card-body">
                    <h4 class="card-title">${r1-title}</h4>
                </div>
        </div>

        <div class="card" onClick="window.open('${r2-href}')">
            <img class="card-img-top" src="${r2-cover}" alt="Card image cap">
                <div class="card-body">
                    <h4 class="card-title">${r2-title}</h4>
                </div>
        </div>

        <div class="card" onClick="window.open('${r3-href}')">
            <img class="card-img-top" src="${r3-cover}" alt="Card image cap">
                <div class="card-body">
                    <h4 class="card-title">${r3-title}</h4>
                </div>
        </div>

        <div class="card" onClick="window.open('${r3-href}')">
            <img class="card-img-top" src="${r3-cover}" alt="Card image cap">
                <div class="card-body">
                    <h4 class="card-title">${r3-title}</h4>
                </div>
        </div>

    </div>
    */
});

function loadInfo(data) {
    var html = '';
    var item;
    item = data['result'];
    rate = item['rate'];
    yellow = [0, 0, 0, 0, 0];
    for (var j = 0; j < 5; j++) {
        yellow[j] = (rate >= (2 * j + 2) ? 2 : max((rate - 2 * j), 0)) * 50;
        item['yellow' + (j + 1)] = yellow[j].toString();
    }
    for (var j = 0; j < item['relative'].length; j++) {
        item['r' + (j + 1) + '-cover'] = item['relative'][j]['cover'];
        item['r' + (j + 1) + '-title'] = item['relative'][j]['title'];
    }
    html += template1(item);

    $('#info').append(html);
    return [item["five_star"], item["four_star"], item["three_star"], item["two_star"], item["one_star"]];
}

function getData() {
    return new Promise((resolve, reject) => {
        fetch("./test/info.json")
            .then(response => response.json())
            .then(data => {
                console.log(data);
                resolve(data);
            })
    });
}

load = async function () {
    var data = await getData();
    var start_percent = loadInfo(data);
    var myChart = echarts.init(document.getElementById('echart'));
    const labelRight = {
        position: 'top'
    };
    option = {
        color: ['#ffd596'],
        title: {
            text: 'Bar Chart with Negative Value',
            show: false
        },
        grid: {
            x: 45,
            y: 15,
            x2: 5,
            y2: 0,
            borderWidth: 1
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        xAxis: {
            type: 'value',
            position: 'top',
            axisLine: {show: false},
            axisLabel: {show: false},
            axisTick: {show: false},
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'category',
            axisLine: {show: false},
            axisLabel: {show: true},
            axisTick: {show: false},
            splitLine: {show: false},
            data: ['5星', '4星', '3星', '2星', '1星']
        },
        series: [
            {
                name: '百分比',
                type: 'bar',
                stack: 'Total',
                label: {
                    show: false,
                    formatter: '{b}'
                },
                itemStyle: {
                    normal: {
                        label: {
                            show: true, //开启显示数值
                            position: 'right', //数值在上方显示
                            textStyle: {
                                //数值样式
                                color: '#000000', //字体颜色
                                fontSize: 14 //字体大小
                            }
                        }
                    }
                },
                data: start_percent,
            }
        ]
    };
    myChart.setOption(option);

}

$(function () {
    load()
})