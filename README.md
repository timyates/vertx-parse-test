To run this, you need to clone [jonnywray/mod-stanford-corenlp](https://github.com/jonnywray/mod-stanford-corenlp) and
run `mvn install` to get a local install of `com.jonnywray.vertx~mod-stanford-corenlp~1.0-SNAPSHOT`

Then, run `vertx run App.groovy`

Then if you telnet to `localhost` port `1234`, you can enter text and see that parse tree returned.

I guess one day, this may be something but for now, that's it...