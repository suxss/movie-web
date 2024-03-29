// import './template.js';
function myparseHTMLTemplate(functionObject) {
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
}


function max(a, b) {
    return a > b ? a : b;
}

var template01 = myparseHTMLTemplate(function () {
    /* <div class="row card-deck" onclick="window.open('info.html?mid=${mid}')">
<div class="col">
    <div class="card box-shadow mb-8">
        <div class="row no-gutters">
            <div class="col-md-2">
                <img class="card-img" src="${cover}" alt="Card image cap" referrerPolicy="no-referrer" >
            </div>
            <div class="col-mb-8">
                <div class="card-body">
                    <h4 class="card-title">${title}</h4>
                    <div class="content">
                        <div class="row" style="width: 400px">
                            <div class="col-6">
                                <div class="el-rate">
                               <span class="el-rate__item" style="cursor: auto;">
                                    <i class="el-rate__icon el-icon-star-on" style="color: rgb(239, 242, 247);">
                                        <i class="el-rate__decimal el-icon-star-on" style="color: rgb(247, 186, 42); width: ${yellow1}%;">
                                        </i>
                                    </i>
                                </span>
                                <span class="el-rate__item" style="cursor: auto;">
                                    <i class="el-rate__icon el-icon-star-on" style="color: rgb(239, 242, 247);">
                                        <i class="el-rate__decimal el-icon-star-on" style="color: rgb(247, 186, 42); width: ${yellow2}%;">
                                        </i>
                                    </i>
                                </span>
                                <span class="el-rate__item" style="cursor: auto;">
                                    <i class="el-rate__icon el-icon-star-on" style="color: rgb(239, 242, 247);">
                                        <i class="el-rate__decimal el-icon-star-on" style="color: rgb(247, 186, 42); width: ${yellow3}%;">
                                        </i>
                                    </i>
                                </span>
                                <span class="el-rate__item" style="cursor: auto;">
                                    <i class="el-rate__icon el-icon-star-on" style="color: rgb(239, 242, 247);">
                                        <i class="el-rate__decimal el-icon-star-on" style="color: rgb(247, 186, 42); width: ${yellow4}%;">
                                        </i>
                                    </i>
                                </span>
                                <span class="el-rate__item" style="cursor: auto;">
                                    <i class="el-rate__icon el-icon-star-on" style="color: rgb(239, 242, 247);">
                                        <i class="el-rate__decimal el-icon-star-on" style="color: rgb(247, 186, 42); width: ${yellow5}%;">
                                        </i>
                                    </i>
                                </span>
                                    <span class="el-rate__text" style="color: rgb(255, 153, 0);">
                                      ${rate}
                                  </span>
                                </div>
                            </div>
                            <div class="col" style="top: -4px">
                                <span class="badge badge-primary">${type}</span>
                            </div>
                        </div>
                        <div class="people">
                            <div class="directors">
                                <p>导演：${director}</p>
                            </div>
                            <div class="actors">
                                <p>主演：${actors}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
    */
});

// console.log(newHtml)

function addCard(data) {
    var item;
    var yellow;
    var rate;
    var newHtml = '';
    for (var i = 0; i < data['result'].length; i++) { //
        item = data['result'][i];
        rate = item['rate'];
        yellow = [0, 0, 0, 0, 0];
        for (var j = 0; j < 5; j++) {
            yellow[j] = (rate >= (2 * j + 2) ? 2 : max((rate - 2 * j), 0)) * 50;
            // console.log(rate, 2*j+2,(rate-2*j), yellow[j]);
            // white[i] = 100 - yellow[i];
            item['yellow' + (j + 1).toString()] = yellow[j].toString();
        }
        newHtml += template01(item);
    }
    // console.log(newHtml);
    $('#search-results').append(newHtml);
}

function getData(key) {
    return new Promise((resolve, reject) => {
        fetch("http://localhost:8080/movie_web/search", {
            method: "post", body: "key=" + key, headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            }
        }).then(response => response.json())
            .then(data => {
                console.log(data);
                resolve(data);
            })
    });
}

async function load(key) {
    var data = await getData(key);
    addCard(data);
}

function getUrlPara(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

$(function () {
    load(getUrlPara("key"))
})


