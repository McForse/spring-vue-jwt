<template>
	<div>
		<h2>{{ content }}</h2>
	</div>
</template>

<script>
	import UserApi from '@/services/api/User'

	export default {
		name: 'UserPage',
		data: () => ({
			content: null,
		}),
		methods: {
			loadContent() {
				UserApi.getContent()
					.then(response => {
						this.content = response.data
					})
					.catch(error => {
						this.notify(error.response.data.message, 'error')
						console.log('ERROR: ' + error.response.data.message)
					})
			},
		},
		mounted() {
			this.loadContent()
		},
	}
</script>

<style>
</style>
