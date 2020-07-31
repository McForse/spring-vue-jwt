import Vue from 'vue'
import Vuex from 'vuex'
import snackbar from '@/store/modules/snackbar'

Vue.use(Vuex)

const state = {
	role: localStorage.getItem('user-role') || '',
	username: localStorage.getItem('user-name') || '',
	authorities: localStorage.getItem('authorities') || '',
}

const getters = {
	isAuthenticated: state => {
		return state.role != null && state.role !== ''
	},
	isAdmin: state => {
		return state.role === 'admin'
	},
	getUsername: state => {
		return state.username
	},
	getAuthorities: state => {
		return state.authorities
	},
}

const mutations = {
	auth_login: (state, user) => {
		localStorage.setItem('user-name', user.username)
		localStorage.setItem('user-authorities', user.roles)
		state.username = user.username
		state.authorities = user.roles
		let isUser = false
		let isAdmin = false
		for (let i = 0; i < user.roles.length; i++) {
			if (user.roles[i].authority === 'ROLE_USER') {
				isUser = true
			} else if (user.roles[i].authority === 'ROLE_ADMIN') {
				isAdmin = true
			}
		}
		if (isUser) {
			localStorage.setItem('user-role', 'user')
			state.role = 'user'
		}
		if (isAdmin) {
			localStorage.setItem('user-role', 'admin')
			state.role = 'admin'
		}
	},
	auth_logout: () => {
		state.role = ''
		state.username = ''
		state.authorities = []
		localStorage.removeItem('user-role')
		localStorage.removeItem('user-name')
		localStorage.removeItem('user-authorities')
	},
}

const actions = {
	login: (context, user) => {
		context.commit('auth_login', user)
	},
	logout: (context) => {
		context.commit('auth_logout')
	},
}

export default new Vuex.Store({
	state,
	getters,
	mutations,
	actions,
	modules: {
		snackbar,
	},
})
