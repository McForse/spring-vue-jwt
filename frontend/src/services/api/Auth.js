import {AXIOS} from '@/services/http-common'

export default {
	login(username, password) {
		return AXIOS.post('/auth/signin', {
			username: username,
			password: password,
		})
	},
	register(username, password) {
		return AXIOS.post('/auth/signup', {
			username: username,
			password: password,
		})
	},
	logout() {
		return AXIOS.post('/auth/logout')
	},
}
