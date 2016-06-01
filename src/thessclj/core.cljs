(ns thessclj.core
  (:require
   #_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true]
   [cljs.test :as t :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defcard thess-clj-meetup
  (sab/html [:div
             [:h2
              "Welcome"]]))

#_(defcard clojure
  (str "
        - Lisp
        - Dynamic
        - Functional
        - Persistent Data Structures
        - Concurrency Model
        - STM"))

#_(defcard lisp
  (str "
        - Code as Data
        - Small
        - Syntactic Abstraction
        - Homoiconicity"))

#_(defn add [x y]
  (+ x y))

#_(defcard learn-clj-in-2-minutes
  (dc/mkdn-pprint-source add))

#_(deftest test-add-function
  (t/testing "addition"
    (t/is (= 5
             (add 3 2))
          "should add two numbers correctly"))
  )

#_(defn langs-comp [data]
  (sab/html
    [:div
     [:div
      [:ul (for [lang (:langs @data)]
             [:li lang])]]
     [:div
      [:input {:type "change" :value (:input @data)
               :onChange #(swap! data assoc-in [:input] (-> % .-target .-value))}]
      [:button {:onClick (fn [e]
                           (swap! data update-in [:langs] conj (:input @data))
                           (swap! data assoc-in [:input] ""))} "Add"]]]))

#_(defcard lang-display
  (fn [data-atom _] (langs-comp data-atom))
  {:langs ["clojure"] :input ""}
  {:inspect-data true
   :frame true
   :history true})


#_(def res-links [["http://www.braveclojure.com/" "Brave Clojure"]
                ["http://clojurekoans.com/" "clj koans"]
                ["http://clojurescriptkoans.com/" "cljs koans"]
                ["https://www.4clojure.com/" "4clojure"]])

#_(defcard resources
  (sab/html
    [:ul
     (for [[link name] res-links]
       [:li [:a {:href link} name]])]))

(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (js/React.render (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html

