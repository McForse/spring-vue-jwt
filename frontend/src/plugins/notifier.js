export default {
	install(Vue) {
		Vue.mixin({
			methods: {
				notify(message, color = 'info') {
					this.$store.commit('snackbar/showMessage', {message, color})
				},
			},
		})
	},
}
