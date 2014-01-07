container.deployModule( "com.jonnywray.vertx~mod-stanford-corenlp~1.0-SNAPSHOT", [ annotators: 'tokenize,ssplit,pos' ] ) { result ->
  vertx.createNetServer().connectHandler { sock ->
    sock.with {
      write banner()
      write prompt()
      dataHandler { buffer ->
        buffer.toString().split( "\r\n" )*.trim().each { line ->
          if( line == '.' ) close()
          else {
            vertx.eventBus.send( 'jonnywray.corenlp', [
              action: 'annotate',
              text: line
            ] ) { reply ->
              write "${new Date()} ${reply.body()}\n"
              write prompt()
            }
          } 
        }
      }
    }
  }.listen( 1234 )
  println "Running...  Telnet to localhost:1234"
}

String banner() {
  '''
  |Vert-x nlp parser test.
  |
  |Send a full stop to quit
  |
  |'''.stripMargin()
}

String prompt() {
  '> '
}