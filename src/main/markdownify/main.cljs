(ns markdownify.main
  (:require [reagent.core :as reagent]
            ["showdown" :as showdown]))

(defonce markdown (reagent/atom ""))

(defonce showdown-converter (showdown/Converter.))

(defn md->html [md]
  (.makeHtml showdown-converter md))

(defn app []
  [:div
   [:h1 "Markdownify"]
   [:div
    {:style {:display :flex}}
    [:div
     {:style {:flex "1"}}
     [:h2 "Markdown"]
     [:textarea
      {:on-change #(reset! markdown (-> % .-target .-value))
       :value @markdown
       :style {:resize "none"
               :height "500px"
               :width "100%"}}]]

    [:div
     {:style {:flex "1"
              :padding-left "2em"}}
     [:h2 "HTML Preview"]
     [:div {:style {:height "500px"}
            :dangerouslySetInnerHTML {:__html (md->html @markdown)}}]]]]) 








(defn mount! []
  (reagent/render [app]
                  (.getElementById js/document "app")))

(defn main! []
  (mount!))

(defn reload! []
  (mount!))

