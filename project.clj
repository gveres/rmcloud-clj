(defproject rmcloud-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [clj-http "3.9.1"]
                 [org.clojure/tools.logging "0.4.1"]
                 [cheshire "5.8.1"]
                 [com.taoensso/sente "1.14.0-RC2"]]
  :plugins [[cider/cider-nrepl "0.18.0"]]
  :repl-options {:init-ns rmcloud-clj.core}
  :profiles {:dev
             {:repl-options
              {:nrepl-middleware
                 [cider.nrepl/wrap-apropos
                  cider.nrepl/wrap-classpath
                  cider.nrepl/wrap-complete
                  cider.nrepl/wrap-debug
                  cider.nrepl/wrap-format
                  cider.nrepl/wrap-info
                  cider.nrepl/wrap-inspect
                  cider.nrepl/wrap-macroexpand
                  cider.nrepl/wrap-ns
                  cider.nrepl/wrap-spec
                  cider.nrepl/wrap-profile
                  cider.nrepl/wrap-refresh
                  cider.nrepl/wrap-resource
                  cider.nrepl/wrap-stacktrace
                  cider.nrepl/wrap-test
                  cider.nrepl/wrap-trace
                  cider.nrepl/wrap-out
                  cider.nrepl/wrap-undef
                  cider.nrepl/wrap-version]}}})

