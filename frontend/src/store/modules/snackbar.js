const state = {
	message: '',
	color: '',
}

const mutations = {
	showMessage(state, payload) {
		state.message = payload.message
		state.color = payload.color
	},
}

export default {
	namespaced: true,
	state,
	mutations,
}