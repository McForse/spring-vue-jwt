import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import notifier from './plugins/notifier'

Vue.config.productionTip = false

export const EventBus = new Vue()

Vue.use(notifier)

new Vue({
	router,
	store,
	vuetify,
	render: h => h(App),
}).$mount('#app')
