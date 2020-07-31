<template>
	<v-app-bar
		app
		dark
		color="primary"
		clipped-left
	>
		<v-app-bar-nav-icon
			@click="toggleDrawer"
		/>

		<v-toolbar-title>Spring Vue JWT</v-toolbar-title>
	</v-app-bar>
</template>

<script>
	import {EventBus} from '@/main'

	export default {
		name: 'AppBar',
		methods: {
			toggleDrawer() {
				EventBus.$emit('toggleDrawer')
			},
		},
	}
</script>

<style>
</style>