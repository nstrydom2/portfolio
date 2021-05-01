var getRandomNumber = function(min, max) {
    return Math.random() * (max - min) + min;
}

// sleep time expects milliseconds
var sleep = function(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}

var greetingTextEffect = async function() {
    var linksElement = document.getElementById("mylinks");
    linksElement.style.visibility = "collapse";

    var welcomeElement = document.getElementById("welcome");
    var welcomeText = welcomeElement.innerHTML;

    //console.log(welcomeText);
    try {
        if (!checkCookie()) {
            welcomeElement.innerHTML = "|";
            await sleep(1000);

            for (const c of welcomeText) {
                welcomeElement.innerHTML = welcomeElement.innerHTML.replace("|", "");
                welcomeElement.innerHTML += c + "|";

                var randomTypoNum = getRandomNumber(7, 12);
                if ((welcomeElement.innerHTML.length % 12) == 0) {
                    var randomChar = String.fromCharCode(getRandomNumber(1, 26) + 96);

                    welcomeElement.innerHTML = welcomeElement.innerHTML.replace("|", "");
                    welcomeElement.innerHTML += randomChar + "|";

                    await sleep(getRandomNumber(60, 140));

                    welcomeElement.innerHTML = welcomeElement.innerHTML.replace("|", "");
                    welcomeElement.innerHTML = welcomeElement.innerHTML.slice(0, -1) + "|";
                }
                await sleep(getRandomNumber(90, 175));
            }

            setCookie(30);
        }
    } catch (err) {

    } finally {
        linksElement.style.visibility = "visible";
        blinkCursorEffect(welcomeElement);
    }
}

var blinkCursorEffect = async function(welcomeElement) {
    while (true) {
        welcomeElement.innerHTML = welcomeElement.innerHTML.slice(0, -1) + "_";
        await sleep(1250);

        welcomeElement.innerHTML = welcomeElement.innerHTML.slice(0, -1) + "|";
        await sleep(1250);
    }
}

var toggleTable = function() {
    var mytable = document.getElementById("mytable")

    if (mytable.style.visibility == "collapse") {
        mytable.style.visibility = "visible";
    } else {
        mytable.style.visibility = "collapse";
    }
}

var linkHoverEffect = function(element) {
    element.innerHTML = element.innerHTML.replace("-", "[");
    element.innerHTML = element.innerHTML.replace("-", "]");
}

var defaultText = function(element) {
    element.innerHTML = element.innerHTML.replace("[", "-");
    element.innerHTML = element.innerHTML.replace("]", "-");
}

var dateInPast = function(firstDate, secondDate) {
  if(firstDate.setHours(0,0,0,0) <= secondDate.setHours(0,0,0,0)) {
    return true;
  }

  return false;
}

// enter some javascript here and it will run
// on every page on this domain (location.host)
var setCookie = function(exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = expires + ";SameSite=Lax;path=/";
}

var getCookie = function(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }

        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }

    return "";
}

var checkCookie = function() {
    var expires = getCookie("expires");
    if (expires == "" || expires == null) {
        return false;
    }

    expires = Date.parse(expires);
    if (dateInPast(expires, new Date())) {
        return false;
    }

    return true;
}