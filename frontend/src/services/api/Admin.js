import {AXIOS} from '@/services/http-common'

export default {
	getContent() {
		return AXIOS.get('/admincontent')
	},
}
