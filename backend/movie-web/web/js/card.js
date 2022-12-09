function addCard(data) {
    for (var i = 0; i < data['result'].length; i++) {
        rate = data['result'][i]['rate'];
        yellow = [0, 0, 0, 0, 0];
        // white = [0, 0, 0, 0, 0];
        for (var j = 0; j < 5; j++) {
            yellow[i] = (rate > i + 1 ? 1 : (rate - i)) * 100;
            // white[i] = 100 - yellow[i];
        }
        html += '<div class="card">';
        html += '<img class="card-img-top" src="' + data['result'][i]['cover'] + '" alt="Card image cap">';
        html += '<div class="card-body">';
        html += '<h4 class="card-title">' + data['result'][i]['title'] + '</h4>';
        html += '<span class="el-rate__item" style="cursor: auto;"><div class="content"><div  class="el-rate">';
        for (var j = 0; j < 5; j++) {
            html += '<span class="el-rate__item" style="cursor: auto;">';
            html += '<i class="el-rate__icon el-icon-star-on" style="color: rgb(239, 242, 247);">';
            html += '<i class="el-rate__decimal el-icon-star-on" style="color: rgb(247, 186, 42); width: ' + yellow[i] + '%;"></i>';
            html += '</i>';
            html += '</span>';
        }
        html += ' <span class="el-rate__text" style="color: rgb(255, 153, 0);">';
        html += data['result'][i]['rate'];
        html += '</span>';
        html += '</div></div></div></div>';
    }
}