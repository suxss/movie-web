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
        <div class="card box-shadow" onclick="window.open('${href}')">
            <img class="card-img-top" src="${cover}" alt="Card image cap" referrerPolicy="no-referrer">
            <div class="card-body">
              <h4 class="card-title">${title}</h4>
              <div class="content">
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
            </div>
        </div>
         */
    });

    function addCard(data) {
        var count = 0;
        var html = '';
        var item;
        for (var i = 0; i < data['result'].length; i++) {
            count += 1;
            if (count % 4 == 1) {
                html += '<div class="row mt-5 card-deck">';
            }
            item = data['result'][i];
            rate = item['rate'];
            yellow = [0, 0, 0, 0, 0];
            // white = [0, 0, 0, 0, 0];
            for (var j = 0; j < 5; j++) {
                yellow[j] = (rate >= (2*j+2) ? 2 : max((rate-2*j), 0))*50;
                item['yellow' + (j+1)] = yellow[j].toString();
            }
            html += template1(item);
            // console.log(item);
            if (count %4 == 0) {
              html += '</div>';
            }
        }
        $('#movieslist').append(html);
    }