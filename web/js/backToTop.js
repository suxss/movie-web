function getTime(scrollTop) {
    var t = 0;
    var speed;
    while (scrollTop > 20) {
        speed = Math.floor(-scrollTop / 6);
        scrollTop += speed;
        t += 50;
    }
    return t;
}

$(function () {
    var loadingGif = lottie.loadAnimation({
        container: document.getElementById('load-animation'), // the dom element that will contain the animation
        renderer: 'svg', //渲染出来的是什么格式
        loop: true,  //循环播放
        autoplay: true, //自动播放
        path: 'data/blue-loading.json' // the path to the animation json
    });


    var toTopGif = lottie.loadAnimation({
        container: document.getElementById('back-to-top-gif'), // the dom element that will contain the animation
        renderer: 'svg', //渲染出来的是什么格式
        loop: false,  //循环播放
        autoplay: false, //自动播放
        path: 'data/to-top.json' // the path to the animation json
    });

    var leader = 0;

    var timer = null;

    window.onscroll = function () {
        if ($(this).scrollTop() > 50) {
            $('#back-to-top').fadeIn();
        } else {
            $('#back-to-top').fadeOut();
        }
        leader = document.documentElement.scrollTop;//每滑动一次获取被卷去的距离
    };

    $('#back-to-top').click(function () {
        const drag = 10;
        const location = 0;

        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        var t = getTime(scrollTop) / 1000;
        console.log(toTopGif.getDuration(), t);
        toTopGif.setSpeed(toTopGif.getDuration() / t);
        toTopGif.playSegments([0, 60], true);
        $('#back-to-top').tooltip('hide');


        clearInterval(timer);
        // timer = setInterval(function(){
        // 	var target = 0;
        // 	var speed = (target - leader)/10;
        // 	speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
        // 	leader = leader + speed;
        // 	window.scrollTo(0,leader); //滚动到指定坐标
        // 	if(leader === 0){
        // 		clearInterval(timer);
        // 	};
        //     console.log(speed, leader, "aaa");
        // },30);

        timer = setInterval(function () {
            //获取滚动条的滚动高度
            var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
            //用于设置速度差，产生缓动的效果
            var speed = Math.floor(-scrollTop / 6);
            document.documentElement.scrollTop = document.body.scrollTop = scrollTop + speed;//用纯数字赋值
            isTop = true;  //用于阻止滚动事件清除定时器
            if (scrollTop === 0) {
                clearInterval(timer);
            }
        }, 50);


        // $('body,html').animate({
        //     scrollTop: 0
        // });


        // window.scrollTo({top: 0, behavior: "smooth"});

        // const gap = document.documentElement.scrollTop || document.body.scrollTop;
        //   if (gap > location) {
        //     window.requestAnimationFrame(scrollToTop);
        //     window.scrollTo(0, gap - gap / drag);
        //   }


        // toTopGif.pause();
        return false;
    });


})