<template>
	<div>
		<h2>{{ content }}</h2>
	</div>
</template>

<script>
	import AdminApi from '@/services/api/Admin'

	export default {
		name: 'AdminPage',
		data: () => ({
			content: null,
		}),
		methods: {
			loadContent() {
				AdminApi.getContent()
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
