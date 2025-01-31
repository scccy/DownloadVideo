class XBogusManager:
    @classmethod
    def str_2_endpoint(
        cls,
        user_agent: str,
        endpoint: str,
    ) -> str:
        try:
            final_endpoint = XB(user_agent).getXBogus(endpoint)
        except Exception as e:
            logger.error(traceback.format_exc())
            raise RuntimeError(_("生成X-Bogus失败: {0})").format(e))

        return final_endpoint[0]

    @classmethod
    def model_2_endpoint(
        cls,
        user_agent: str,
        base_endpoint: str,
        params: dict,
    ) -> str:
        if not isinstance(params, dict):
            raise TypeError(_("参数必须是字典类型"))

        param_str = "&".join([f"{k}={v}" for k, v in params.items()])

        try:
            xb_value = XB(user_agent).getXBogus(param_str)
        except Exception as e:
            logger.error(traceback.format_exc())
            raise RuntimeError(_("生成X-Bogus失败: {0})").format(e))

        # 检查base_endpoint是否已有查询参数 (Check if base_endpoint already has query parameters)
        separator = "&" if "?" in base_endpoint else "?"

        final_endpoint = f"{base_endpoint}{separator}{param_str}&X-Bogus={xb_value[1]}"

        return final_endpoint